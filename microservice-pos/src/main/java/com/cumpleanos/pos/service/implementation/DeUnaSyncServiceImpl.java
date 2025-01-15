package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.exception.ApiResponse;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoRequest;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentRequest;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;
import com.cumpleanos.pos.persistence.entity.ReciboPOS;
import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import com.cumpleanos.pos.persistence.ids.ReciboPOSId;
import com.cumpleanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumpleanos.pos.persistence.repository.ReciboPOSViewRepositorio;
import com.cumpleanos.pos.service.exception.InfoPaymentException;
import com.cumpleanos.pos.service.exception.ReciboNotFoundException;
import com.cumpleanos.pos.service.exception.TimeoutException;
import com.cumpleanos.pos.service.interfaces.IDeUnaSyncService;
import com.cumpleanos.pos.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class DeUnaSyncServiceImpl implements IDeUnaSyncService {

    private final ReciboPOSRepository reciboPOSRepository;
    private final ReciboPOSViewRepositorio viewRepositorio;
    private final ModelsClientServiceImpl modelsClientService;
    private final DeunaPaymentClientService deunaClientService;

    @Override
    public ApiResponse<PaymentResponse> procesarPago(Long usrLiquida, Long empresa) {
        ReciboPOSView view = viewRepositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa).orElseThrow(() ->
                new RuntimeException("Recibo no encontrado")
        );
        PaymentRequest request = createPaymentRequest(view);
        ApiResponse<PaymentResponse> response = deunaClientService.getPayment(request);
        if (response.getError() != null) {
            log.error("Error al procesar pago de recibo: {}", response.getError());
            return response;
        }
        actualizarReciboPosSend(view, response.getData(), request);
        return response;
    }

    @Override
    public InfoResponse procesarInfoPayment(Long usrLiquida, Long empresa) {
        ReciboPOSView view = viewRepositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa)
                .orElseThrow(() -> new ReciboNotFoundException("Recibo no encontrado"));
        InfoRequest request = createInfoRequest(view);
        return esperarAprobacion(view, request);
    }

    private PaymentRequest createPaymentRequest(ReciboPOSView v) {
        ApiResponse<Sistema> empresa = modelsClientService.getEmpresa(v.getEmpresa());
        if (empresa.getData() == null) {
            throw new RuntimeException("Error al obtener la empresa");
        }
        String detail = "Compra en " + empresa.getData().getNombrecorto();
        String codigoPuntoVenta = String.valueOf(v.getCodigo()) + String.valueOf(v.getPventa());
        String internalTransactioonReference = DateUtils.obtenerFechaHora() + codigoPuntoVenta;
        return new PaymentRequest(
                v.getCapId(),
                "dynamic",
                v.getTotal().doubleValue(),
                detail.toUpperCase(),
                internalTransactioonReference,
                "2"
        );
    }

    private InfoRequest createInfoRequest(ReciboPOSView v) {
        return new InfoRequest(
                v.getReferencia(),
                "0"
        );
    }

    private void actualizarReciboPosSend(ReciboPOSView v, PaymentResponse resp, PaymentRequest req) {
        ReciboPOSId id = new ReciboPOSId(v.getRpoCodigo(), v.getEmpresa());
        ReciboPOS reciboPOS = reciboPOSRepository.findById(id)
                .orElseThrow(() -> new ReciboNotFoundException("No se encontraron datos en la vista Recibo"));

        actualizarReciboPOS(reciboPOS, resp.transactionId(), req.internalTransactionReference());
        try {
            reciboPOSRepository.saveAndFlush(reciboPOS);
        } catch (Exception e) {
            log.error("Error al actualizar recibo pos: {}", e.getMessage());
        }
    }

    private void actualizarReciboPOS(ReciboPOS pos, String transactionId, String internalTransactionReference) {
        pos.setNroDoc(internalTransactionReference);
        pos.setReferencia(transactionId);
        pos.setHora(DateUtils.obtenerHora());
        pos.setFecha(DateUtils.obtenerFecha());
    }

    private void actualizarReciboPosAcepted(ReciboPOSView v, InfoResponse resp) {
        ReciboPOSId id = new ReciboPOSId(v.getRpoCodigo(), v.getEmpresa());
        ReciboPOS reciboPOS = reciboPOSRepository.findById(id)
                .orElseThrow(() -> new ReciboNotFoundException("No se encontraron datos en la vista Recibo"));
        grabarPOSFinalizado(reciboPOS, resp);
        try {
            reciboPOSRepository.save(reciboPOS);
        } catch (Exception e) {
            log.error("Error al Grabar recibo pos: {}", e.getMessage());
        }
    }

    private void grabarPOSFinalizado(ReciboPOS pos, InfoResponse resp) {
        pos.setTarjetaHabiente(resp.ordererName());
        pos.setNumAprob(resp.transferNumber());
        pos.setResultado(resp.status());
        pos.setIdentificacionCli(resp.ordererIdentification());
    }

    private InfoResponse esperarAprobacion(ReciboPOSView view, InfoRequest request) {
        int intentosMaximos = 20;
        int intervaloEspera = 2000; // En milisegundos 2 segundos
        int intentos = 0;

        while (intentos < intentosMaximos) {
            try {
                ApiResponse<InfoResponse> response = deunaClientService.getInfo(request);
                log.info("Esperando respuesta - intento {}, Respuesta: {}", intentos, response.getData().status());

                if (response.getError() != null) {
                    log.error("Error al obtener la respuesta de pago de recibo: {}", response.getError());
                    throw new InfoPaymentException("Error en respuesta: " + response.getError());
                }

                if ("APPROVED".equalsIgnoreCase(response.getData().status())) {
                    actualizarReciboPosAcepted(view, response.getData());
                    return response.getData();
                }

                intentos++;
                Thread.sleep(intervaloEspera);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restablecer el estado de interrupción del hilo
                throw new InfoPaymentException("Interrupción mientras se esperaba aprobación", e);
            } catch (Exception e) {
                log.error("Error inesperado: {}", e.getMessage());
                throw new InfoPaymentException("Error inesperado durante la aprobación", e);
            }
        }
        log.error("Tiempo de espera excedido después de {} intentos.", intentosMaximos);
        throw new TimeoutException("Tiempo de espera agotado.");
    }

}

package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.common.exception.ApiResponse;
import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.jep.JepRequestQr;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import com.cumpleanos.pos.persistence.api.jep.NotificacionJep;
import com.cumpleanos.pos.persistence.entity.ReciboPOS;
import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import com.cumpleanos.pos.persistence.ids.ReciboPOSId;
import com.cumpleanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumpleanos.pos.persistence.repository.ReciboPOSViewRepositorio;
import com.cumpleanos.pos.service.exception.InfoPaymentException;
import com.cumpleanos.pos.service.exception.ReciboNotFoundException;
import com.cumpleanos.pos.service.interfaces.IJepFasterSyncService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import static com.cumpleanos.pos.utils.DateUtils.*;
import static com.cumpleanos.pos.utils.StringUtils.getTransactionReference;

@Slf4j
@Service
@PropertySource("classpath:api-keys.properties")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JepFasterSyncServiceImpl implements IJepFasterSyncService {

    private final ReciboPOSRepository reciboPOSRepository;
    private final ReciboPOSViewRepositorio viewRepositorio;
    private final ModelsClientServiceImpl modelsClientService;
    private final JepFasterClientService jepFasterClientService;

    @Value("${usuario-jep}")
    private String usuarioJep;
    @Value("${password-jep}")
    private String passwordJep;

    @Override
    public JepResponseQr generarQR(Long usrLiquida, Long empresa) {
        ReciboPOSView view = viewRepositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa).orElseThrow(() ->
                new RuntimeException("Recibo no encontrado")
        );
        JepRequestQr request = createRequest(view);
        ApiResponse<JepResponseQr> response = jepFasterClientService.getQR(request);
        if (response.getError() != null) {
            log.error("Error al generar QR: {}", response.getError());
            throw new InfoPaymentException("No se pudo generar el QR: " + response.getError());
        }
        actualizarReciboPosInicial(view, request.codigoTransaccion());
        return response.getData();
    }

    @Override
    public ServiceResponse validarPago(Long usrLiquida, Long empresa) {
        return esperarAprobacion(usrLiquida, empresa);
    }

    @Override
    public ServiceResponse procesarPago(NotificacionJep notification) {
        log.info("----> Notificacion recibida: {}", notification.toString());

        ReciboPOS pos = reciboPOSRepository.findByReferencia(notification.idtransaccion()).orElseThrow(() -> new EntityNotFoundException("No se encontraron datos en la vista Recibo"));

        pos.setNumAprob(notification.nummensaje());
        pos.setResultado(notification.estado());
        pos.setAprobado(true);

        reciboPOSRepository.save(pos);
        return new ServiceResponse("Notificacion recibida",true);
    }

    @Override
    public ServiceResponse verificarPago(Long usrLiquida, Long empresa) {
        ReciboPOSView view = viewRepositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa).orElseThrow(() ->
                new RuntimeException("Recibo no encontrado")
        );
        if (view.getResultado() != null && view.getResultado().equalsIgnoreCase("PAGADO")){
            return new ServiceResponse("PAGADO", Boolean.TRUE);
        }
        return new ServiceResponse("NO PAGADO", Boolean.FALSE);
    }

    private JepRequestQr createRequest(ReciboPOSView v) {
        ApiResponse<AlmacenDTO> almacen = modelsClientService.getAlmacen(v.getEmpresa(), v.getAlmacen());
        if (almacen.getData() == null) {
            throw new RuntimeException("Error al obtener la informacion de la sucursal");
        }

        String codigoTransaccion = v.getEmpresa() + v.getAlmId() + v.getPventa() + obtenerFechaHora();

        return new JepRequestQr(
                usuarioJep,
                passwordJep,
                String.valueOf(v.getTotal()),
                codigoTransaccion,
                v.getCapId(),
                null,
                almacen.getData().ciudad(),
                almacen.getData().direccion(),
                almacen.getData().nombre(),
                null
        );
    }

    private void actualizarReciboPosInicial(ReciboPOSView v, String codigoTransaccion){
        ReciboPOSId id = new ReciboPOSId(v.getRpoCodigo(), v.getEmpresa());
        ReciboPOS recibo = reciboPOSRepository.findById(id)
                .orElseThrow(() -> new ReciboNotFoundException("No se encontraron datos en la vista Recibo"));

        String transactionReference = getTransactionReference(v);

        recibo.setNroDoc(transactionReference);
        recibo.setReferencia(codigoTransaccion);
        recibo.setHora(obtenerHora());
        recibo.setFecha(obtenerFecha());

        reciboPOSRepository.save(recibo);
    }

    private ServiceResponse esperarAprobacion(Long usrLiq, Long empresa){
        int intentosMaximos = 150; //cinco minutos de espera
        int intervaloEspera = 2000; // En milisegundos 2 segundos
        int intentos = 0;

        while (intentos < intentosMaximos){
            try{
                ReciboPOSView view = viewRepositorio.findByUsrLiquidaAndEmpresa(usrLiq, empresa).orElseThrow(() ->
                        new RuntimeException("Recibo no encontrado")
                );
                log.info("Esperando respuesta - intento {}, Respuesta: {}", intentos, view.getResultado());

                if (view.getResultado() != null && view.getResultado().equalsIgnoreCase("PAGADO")){
                    return new ServiceResponse("PAGADO", Boolean.TRUE);
                }

                intentos++;
                Thread.sleep(intervaloEspera);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt(); // Restablecer el estado de interrupción del hilo
                throw new InfoPaymentException("Interrupción mientras se esperaba aprobación", e);
            } catch (Exception e){
                log.error("Error inesperado: {}", e.getMessage());
                throw new InfoPaymentException("Error esperandola aprobacion de JepFaster", e);
            }
        }
        log.error("Tiempo de espera excedido después de {} intentos.", intentosMaximos);
        throw new InfoPaymentException("No se pudo procesar su pago verifique su estado de cuenta");
    }
}

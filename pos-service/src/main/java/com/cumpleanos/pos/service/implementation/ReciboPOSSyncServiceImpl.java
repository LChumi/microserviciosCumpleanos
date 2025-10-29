package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.pos.persistence.api.datapos.DatosEnvioRequest;
import com.cumpleanos.pos.persistence.api.datapos.DatosRecepcionResponse;
import com.cumpleanos.pos.persistence.entity.ReciboPOS;
import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import com.cumpleanos.pos.persistence.ids.ReciboPOSId;
import com.cumpleanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumpleanos.pos.persistence.repository.ReciboPOSViewRepositorio;
import com.cumpleanos.pos.service.exception.InfoPaymentException;
import com.cumpleanos.pos.service.exception.ReciboNotFoundException;
import com.cumpleanos.pos.service.interfaces.IReciboPOSSyncService;
import com.cumpleanos.pos.service.http.ApiConsumoService;
import com.cumpleanos.pos.utils.FilesUtils;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static com.cumpleanos.pos.utils.DateUtils.obtenerFecha;
import static com.cumpleanos.pos.utils.DateUtils.obtenerHora;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ReciboPOSSyncServiceImpl implements IReciboPOSSyncService {

    private final ReciboPOSViewRepositorio repositorio;
    private final ApiConsumoService apiService;
    private final ReciboPOSRepository reciboPOSRepository;
    private final FilesUtils filesUtils;

    @Override
    @Transactional
    public String procesarPago(Long usrLiquida, Long empresa) {
        try {
            log.info("Iniciar Transaccion pago POS via COM");
            ReciboPOSView reciboPOSView = obtenerReciboPosView(usrLiquida, empresa);

            DatosEnvioRequest dEnvio = crearDatosEnvioRequest(reciboPOSView);

            DatosRecepcionResponse response = apiService.procesarPago(reciboPOSView.getIp(), reciboPOSView.getPuertoCom(), dEnvio);

            validateDatosRecepcion(response);

            actualizaGuardarReciboPOS(reciboPOSView, response);

            return "1";
        } catch (DataAccessException | PersistenceException e) {
            log.error("ERROR de acceso a datos al procesar el pago: {}", e.getMessage(), e);
            return "Error de acceso a datos: " + e.getMessage();
        } catch (Exception e) {
            log.error("ERROR al procesar el pago: {}", e.getMessage(), e);
            return e.getMessage();
        }
    }

    @Override
    public Map<String, String> listarPuertos(Long usrLiquida, Long empresa) {
        ReciboPOSView v = repositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa)
                .orElseThrow(() -> new ReciboNotFoundException("No se encontraron datos en la vista Recibo POS por UsrLiquida"));
        return apiService.listarPuertos(v.getIp());
    }

    @Override
    @Transactional
    public String anularPago(Long usrLiquida, Long empresa) {
        try {
            log.info("Iniciar Transaccion anulacion POS via COM");
            ReciboPOSView v = obtenerReciboPosView(usrLiquida, empresa);

            DatosRecepcionResponse response = apiService.anularPago(v.getIp(), v.getPuertoCom(), v.getReferencia());

            validateDatosRecepcion(response);

            return getRecibo(v, response);

        } catch (Exception e) {
            log.error("ERROR al anular el pago {}", e.getMessage(), e);
            return e.getMessage();
        }
    }

    @Override
    public String procesarPagoLan(Long usrLiquida, Long empresa) {
        try {
            ReciboPOSView reciboPOSView = obtenerReciboPosView(usrLiquida, empresa);
            log.info("Iniciando proceso de generar pago POS via LAN en la empresa {}, pventa, {}, de valor {}", empresa, reciboPOSView.getPventa(), reciboPOSView.getTotal());

            DatosEnvioRequest dEnvio = crearDatosEnvioRequest(reciboPOSView);

            DatosRecepcionResponse response = apiService.procesarPagoLan(reciboPOSView.getIp(), reciboPOSView.getPuertoDtf(), reciboPOSView.getIp_dtf(), dEnvio);

            validateDatosRecepcion(response);

            actualizaGuardarReciboPOS(reciboPOSView, response);

            return "1";
        } catch (DataAccessException | PersistenceException e) {
            log.error("ERROR de acceso a datos al procesar el pago: {}", e.getMessage(), e);
            return "Error de acceso a datos: " + e.getMessage();
        } catch (Exception e) {
            log.error("ERROR al procesar el pago: {}", e.getMessage(), e);
            return e.getMessage();
        }
    }

    @Override
    public String anularPagoLan(Long usrLiquida, Long empresa) {
        try {
            ReciboPOSView v = obtenerReciboPosView(usrLiquida, empresa);
            log.info("Inicializar Anulacion POS via LAN en la empresa {}, pventa, {}, de valor {}", empresa, v.getPventa(), v.getTotal());

            DatosRecepcionResponse response = apiService.anularPagoLan(v.getIp(), v.getPuertoDtf(), v.getIp_dtf(), v.getReferencia());

            validateDatosRecepcion(response);

            return getRecibo(v, response);
        } catch (Exception e) {
            log.error("ERROR al anular el pago {}", e.getMessage(), e);
            return e.getMessage();
        }
    }

    private String getRecibo(ReciboPOSView v, DatosRecepcionResponse response) {
        ReciboPOSId id = new ReciboPOSId(v.getRpoCodigo(), v.getEmpresa());
        ReciboPOS reciboPOS = reciboPOSRepository.findById(id)
                .orElseThrow(() -> new ReciboNotFoundException("No se encontraron datos en la vista Recibo"));

        actualizarReciboPOS(reciboPOS, response);
        reciboPOS.setAnulado(true);
        reciboPOSRepository.save(reciboPOS);
        return "1";
    }

    private ReciboPOSView obtenerReciboPosView(Long usrLiquida, Long empresa) {
        return repositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa)
                .orElseThrow(() -> new ReciboNotFoundException("No se encontraron datos en la vista Recibo POS para UsrLiquida: " + usrLiquida + ", empresa: " + empresa ));
    }

    private DatosEnvioRequest crearDatosEnvioRequest(ReciboPOSView v) {
        DatosEnvioRequest dEnvio = new DatosEnvioRequest();

        if (v.getDescuento() == null || BigDecimal.ZERO.compareTo(v.getDescuento()) == 0) {
            dEnvio.setBaseImponible(v.getSubtotal().doubleValue());
        } else {
            BigDecimal subtotal = v.getSubtotal().subtract(v.getDescuento());
            dEnvio.setBaseImponible(subtotal.doubleValue());
        }

        dEnvio.setBase0(v.getSubtotal0().doubleValue());
        dEnvio.setIva(v.getValImpuesto().doubleValue());

        Long cuotasValor = v.getCuotas();
        int cuotas = (cuotasValor != null && cuotasValor > 0) ? Math.toIntExact(cuotasValor) : 1;
        dEnvio.setCuotas(cuotas);

        dEnvio.setTipoCredito(v.getTipoCredito());

        if (cuotas > 0) {
            dEnvio.setTipoTransaccion("1");
        }

        return dEnvio;
    }

    private void actualizaGuardarReciboPOS(ReciboPOSView v, DatosRecepcionResponse response) {
        ReciboPOSId id = new ReciboPOSId(v.getRpoCodigo(), v.getEmpresa());
        ReciboPOS reciboPOS = reciboPOSRepository.findById(id)
                .orElseThrow(() -> new ReciboNotFoundException("No se encontraron datos en la vista Recibo"));
        actualizarReciboPOS(reciboPOS, response);
        try {
            reciboPOSRepository.save(reciboPOS);
        } catch (DataAccessException | PersistenceException e) {
            log.error("ERROR de acceso a datos al actualizar el pago: {}", e.getMessage(), e);
            filesUtils.crearArchivoDatosNoGuardado(v.getUsrLiquida(), v.getEmpresa(), response);
        }
    }

    private void actualizarReciboPOS(ReciboPOS reciboPOS, DatosRecepcionResponse response) {
        String tarjetaCliente = response.getTarjetaHabiente().trim().toUpperCase();
        reciboPOS.setTarjetaHabiente(tarjetaCliente);
        reciboPOS.setNumAprob(response.getNumeroAprobacion());
        reciboPOS.setNomEmisor(response.getNombreEmisor());
        reciboPOS.setReferencia(response.getReferencia());
        reciboPOS.setLote(response.getLote());
        reciboPOS.setNomAdquiriente(response.getNombreAdquiriente());
        reciboPOS.setNumTarjeta(response.getNumeroTarjeta());
        reciboPOS.setResultado(response.getMensajeResultado());
        reciboPOS.setFecha(obtenerFecha());
        reciboPOS.setHora(obtenerHora());
        reciboPOS.setAprobado(true);
    }

    private void validateDatosRecepcion(DatosRecepcionResponse response) {
        if (response == null) {
            throw new InfoPaymentException("No se recibio respuesta de la entidad.");
        }

        if (!"Transacción Aprobada".equalsIgnoreCase(response.getMensajeResultado())) {
            throw new InfoPaymentException(response.getMensajeResultado());
        }

        if (isBlank(response.getNumeroAprobacion()) ||
                isBlank(response.getCodigoAdquiriente()) ||
                isBlank(response.getNombreAdquiriente()) ||
                isBlank(response.getNombreEmisor())) {
            throw new InfoPaymentException("Transaccion no aprobada por la entidad ...");
        }
        log.info("Transaccion Aprobada por la entidad respuesta {}, aprobacion #:{} referencia#: {}", response.getNombreEmisor(), response.getNumeroAprobacion(), response.getReferencia());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

}
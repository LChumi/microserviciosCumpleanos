package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.pos.persistence.api.datapos.DatosEnvioRequest;
import com.cumpleanos.pos.persistence.api.datapos.DatosRecepcionResponse;
import com.cumpleanos.pos.persistence.entity.ReciboPOS;
import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import com.cumpleanos.pos.persistence.ids.ReciboPOSId;
import com.cumpleanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumpleanos.pos.persistence.repository.ReciboPOSViewRepositorio;
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

import java.util.Map;

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
            ReciboPOSView reciboPOSView = obtenerReciboPosView(usrLiquida, empresa);

            DatosEnvioRequest dEnvio = crearDatosEnvioRequest(reciboPOSView);

            DatosRecepcionResponse response = apiService.procesarPago(reciboPOSView.getIp(), reciboPOSView.getPuertoCom(), dEnvio);
            if (response == null) {
                throw new RuntimeException("No se pudo procesar el Recibo POS sin respuesta");
            }

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
                .orElseThrow(() -> new RuntimeException("No se encontraron datos en la vista Recibo POS por UsrLiquida"));
        return apiService.listarPuertos(v.getIp());
    }

    @Override
    @Transactional
    public String anularPago(Long usrLiquida, Long empresa) {
        try {
            ReciboPOSView v = obtenerReciboPosView(usrLiquida, empresa);

            DatosRecepcionResponse response = apiService.anularPago(v.getIp(), v.getPuertoCom(), v.getReferencia());
            if (response == null) {
                return "No se pudo procesar la anulación, respuesta nula";
            } else {
                ReciboPOSId id = new ReciboPOSId(v.getRpoCodigo(), v.getEmpresa());
                ReciboPOS reciboPOS = reciboPOSRepository.findById(id)
                        .orElseThrow(() -> new ReciboNotFoundException("No se encontraron datos en la vista Recibo"));

                actualizarReciboPOS(reciboPOS, response);
                reciboPOS.setAnulado(true);
                reciboPOSRepository.save(reciboPOS);
                return "1";
            }
        } catch (Exception e) {
            log.error("ERROR al anular el pago {}", e.getMessage(), e);
            return e.getMessage();
        }
    }

    private ReciboPOSView obtenerReciboPosView(Long usrLiquida, Long empresa) {
        return repositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa)
                .orElseThrow(() -> new RuntimeException("No se encontraron datos en la vista Recibo POS para UsrLiquida y Empresa"));
    }

    private DatosEnvioRequest crearDatosEnvioRequest(ReciboPOSView v) {
        DatosEnvioRequest dEnvio = new DatosEnvioRequest();
        dEnvio.setBaseImponible(v.getSubtotal().doubleValue());
        dEnvio.setBase0(v.getSubtotal0().doubleValue());
        dEnvio.setIva(v.getValImpuesto().doubleValue());
        dEnvio.setCuotas(Math.toIntExact(v.getCuotas()));
        dEnvio.setTipoCredito(v.getTipoCredito());
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
        reciboPOS.setFecha(response.getFecha());
        reciboPOS.setHora(response.getHora());
        reciboPOS.setAprobado(true);
    }

}
package com.cumlpeanos.pos.service.implementation;

import com.cumlpeanos.pos.persistence.api.DatosEnvioRequest;
import com.cumlpeanos.pos.persistence.api.DatosRecepcionResponse;
import com.cumlpeanos.pos.persistence.entity.ReciboPOS;
import com.cumlpeanos.pos.persistence.entity.ReciboPOSView;
import com.cumlpeanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumlpeanos.pos.persistence.repository.ReciboPOSViewRepositorio;
import com.cumlpeanos.pos.service.interfaces.IReciboPOSSyncService;
import com.cumlpeanos.pos.service.http.ApiConsumoService;
import com.cumlpeanos.pos.utils.FilesUtils;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
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

            DatosRecepcionResponse response = apiService.procesarPago(reciboPOSView.getIp(),reciboPOSView.getPuertoCom(),dEnvio);
            if (response == null) {
                throw new RuntimeException("No se pudo procesar el Recibo POS sin respuesta");
            }

            actualizaGuardarReciboPOS(reciboPOSView,response);

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
                ReciboPOS reciboPOS = reciboPOSRepository.findByIdAndEmpresa(v.getRpoCodigo(), v.getEmpresa())
                        .orElseThrow(() -> new RuntimeException("No se encontró el recibo POS"));

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

    private DatosEnvioRequest crearDatosEnvioRequest(ReciboPOSView v){
        DatosEnvioRequest dEnvio = new DatosEnvioRequest();
        dEnvio.setBaseImponible(v.getSubtotal().doubleValue());
        dEnvio.setBase0(v.getSubtotal0().doubleValue());
        dEnvio.setIva(v.getValImpuesto().doubleValue());
        dEnvio.setCuotas(Math.toIntExact(v.getCuotas()));
        dEnvio.setTipoCredito(v.getTipoCredito());
        return dEnvio;
    }

    private void actualizaGuardarReciboPOS(ReciboPOSView v, DatosRecepcionResponse response){
        ReciboPOS reciboPOS = reciboPOSRepository.findByIdAndEmpresa(v.getRpoCodigo(), v.getEmpresa())
                .orElseThrow(() -> new RuntimeException("No se encontraron datos en la vista Recibo"));
        actualizarReciboPOS(reciboPOS,response);
        try {
            reciboPOSRepository.save(reciboPOS);
        } catch (DataAccessException | PersistenceException e){
            log.error("ERROR de acceso a datos al actualizar el pago: {}", e.getMessage(), e);
            filesUtils.crearArchivoDatosNoGuardado(v.getUsrLiquida(),v.getEmpresa(),response);
        }
    }

    private void actualizarReciboPOS(ReciboPOS reciboPOS, DatosRecepcionResponse response) {
        reciboPOS.setTarjetaHabiente(response.getTarjetaHabiente());
        reciboPOS.setNumAprob(response.getNumeroAprobacion());
        reciboPOS.setNomEmisor(response.getNombreEmisor());
        reciboPOS.setReferencia(response.getReferencia());
        reciboPOS.setLote(response.getLote());
        reciboPOS.setNomAdquiriente(response.getNombreAdquiriente());
        reciboPOS.setNumTarjeta(response.getNumeroTarjeta());
        reciboPOS.setResultado(response.getMensajeResultado());
        reciboPOS.setFecha(response.getFecha());
        reciboPOS.setHora(response.getHora());
    }

}
package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.api.DatosEnvioRequest;
import com.cumlpeanos.pos.models.api.DatosRecepcionResponse;
import com.cumlpeanos.pos.models.entity.ReciboPOS;
import com.cumlpeanos.pos.models.entity.ReciboPOSView;
import com.cumlpeanos.pos.repository.ReciboPOSRepository;
import com.cumlpeanos.pos.repository.ReciboPOSViewRepositorio;
import com.cumlpeanos.pos.service.api.ApiConsumoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReciboPOSSyncServiceImpl implements IReciboPOSSyncService{

    private final ReciboPOSViewRepositorio repositorio;
    private final ApiConsumoService apiService;
    private final ReciboPOSRepository reciboPOSRepository;

    @Override
    public String procesarPago(Long usrLiquida, Long empresa) {
        try {
            ReciboPOSView v= repositorio.findByUsrLiquidaAndEmpresa(usrLiquida,empresa)
                    .orElseThrow(()-> new RuntimeException("No se encontro datos en la vista Recibo POS por UsrLiquida "));

            DatosEnvioRequest dEnvio= new DatosEnvioRequest();
            dEnvio.setBaseImponible(v.getSubtotal().doubleValue());
            dEnvio.setBase0(v.getSubtotal0().doubleValue());
            dEnvio.setIva(v.getValImpuesto().doubleValue());
            dEnvio.setCuotas(0);
            dEnvio.setTipoCredito(v.getTcrId());

            DatosRecepcionResponse response=apiService.procesarPago(v.getIp(),v.getPuertoCom(),dEnvio);
            if (response == null){
                return "No se puedo procesar el Recibo POS sin respuesta ";
            }else{
                //update bd recibo_pos repositorio.update
                ReciboPOS reciboPOS = reciboPOSRepository.findByIdAndEmpresa(v.getRpoCodigo(),v.getEmpresa())
                        .orElseThrow(() -> new RuntimeException("No se encontro el recibo POS"));
                reciboPOS.setTarjetaHabiente(response.getTarjetaHabiente());
                reciboPOS.setNum_aprob(response.getNumeroAprobacion());
                reciboPOS.setNom_emisor(response.getNombreEmisor());
                reciboPOS.setReferencia(response.getReferencia());
                reciboPOS.setLote(response.getLote());
                reciboPOS.setNomAdquiriente(response.getNombreAdquiriente());
                reciboPOS.setNum_tarjeta(response.getNumeroTarjeta());
                reciboPOS.setResultado(response.getMensajeResultado());
                reciboPOS.setFecha(response.getFecha());
                reciboPOS.setHora(response.getHora());
                reciboPOSRepository.save(reciboPOS);
                return "1";
            }
        } catch (Exception e){
            log.error("ERROR al procesar el pago {}",e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public Map<String,String> listarPuertos(Long usrLiquida, Long empresa) {
        ReciboPOSView v= repositorio.findByUsrLiquidaAndEmpresa(usrLiquida,empresa)
                .orElseThrow(()-> new RuntimeException("No se encontro datos en la vista Recibo POS por UsrLiquida "));
        return apiService.listarPuertos(v.getIp());
    }

    @Override
    public String anularPago(Long usrLiquida, Long empresa) {
        try {
            ReciboPOSView v= repositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa)
                    .orElseThrow(() -> new RuntimeException("No se encontro datos en la vista Recibo POS por UsrLiquida "));

            DatosRecepcionResponse response = apiService.anularPago(v.getIp(),v.getPuertoCom(),v.getReferencia());
            if (response == null){
                return "No se pudo procesar la anulacion respuesta nula ";
            } else {
                ReciboPOS reciboPOS = reciboPOSRepository.findByIdAndEmpresa(v.getRpoCodigo(),v.getEmpresa())
                        .orElseThrow(() -> new RuntimeException("No se encontro el recibo POS"));

                reciboPOS.setTarjetaHabiente(response.getTarjetaHabiente());
                reciboPOS.setNum_aprob(response.getNumeroAprobacion());
                reciboPOS.setNom_emisor(response.getNombreEmisor());
                reciboPOS.setReferencia(response.getReferencia());
                reciboPOS.setLote(response.getLote());
                reciboPOS.setNomAdquiriente(response.getNombreAdquiriente());
                reciboPOS.setNum_tarjeta(response.getNumeroTarjeta());
                reciboPOS.setResultado(response.getMensajeResultado());
                reciboPOS.setFecha(response.getFecha());
                reciboPOS.setHora(response.getHora());
                reciboPOS.setAnulado(1L);
                return "1";
            }
        } catch (Exception e){
            log.error("ERROR al anular el pago {}",e.getMessage());
            return e.getMessage();
        }
    }


}

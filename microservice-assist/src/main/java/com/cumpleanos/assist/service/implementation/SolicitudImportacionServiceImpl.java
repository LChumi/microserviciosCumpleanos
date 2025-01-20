package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.dto.SolicitudRequestDTO;
import com.cumpleanos.assist.persistence.repository.functions.FunctionOracleRepository;
import com.cumpleanos.assist.persistence.repository.functions.ProcedureOracleRepository;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.service.exception.ProcedureNotCompletedException;
import com.cumpleanos.assist.service.http.IModelsClient;
import com.cumpleanos.assist.service.interfaces.ISolicitudImportacionService;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.DfacturaId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class SolicitudImportacionServiceImpl implements ISolicitudImportacionService {

    private final ProcedureOracleRepository procedureRepository;
    private final FunctionOracleRepository functionRepository;
    private final IModelsClient modelsClient;

    @Override
    public String procesarSolicitud(SolicitudRequestDTO solicitudRequestDTO) {

        try {
            BigInteger cco= procedureRepository.getCabeceraIdByProcedure(
                    solicitudRequestDTO.getEmpresa(),
                    solicitudRequestDTO.getTipodoc(),
                    solicitudRequestDTO.getAlmacen(),
                    solicitudRequestDTO.getPventa(),
                    solicitudRequestDTO.getSigla(),
                    solicitudRequestDTO.getProveedor(),
                    solicitudRequestDTO.getUsuario(),
                    solicitudRequestDTO.getFecha(),
                    solicitudRequestDTO.getModulo()
            );
        } catch (ProcedureNotCompletedException e) {
            log.error("Error al generar la cabecera SCI: {}", e.getMessage(), e);
            throw e;
        }
        return "";
    }

     private String getComprobante(Long empresa, BigInteger coo) {
        try {
            return functionRepository.getComprobante(empresa, coo);
        } catch (Exception e) {
            throw new ProcedureNotCompletedException("Error al obtener el comprobante", e);
        }
    }

    private void createDfacturas(Long empresa, BigInteger cco , List<ProductImportTransformer> items){
        long cont = 0L;
        for (ProductImportTransformer item : items) {
            Dfactura detalle = new Dfactura();
            DfacturaId id = new DfacturaId();
            id.setEmpresa(empresa);
            id.setSecuencia(cont++);
            id.setCfacComproba(cco);

            detalle.setId(id);
        }
    }
}

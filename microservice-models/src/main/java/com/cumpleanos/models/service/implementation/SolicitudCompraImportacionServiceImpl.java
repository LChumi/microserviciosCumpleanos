package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.dto.DfacturaDTO;
import com.cumpleanos.core.models.dto.SolicitudCompraImportacionDTO;
import com.cumpleanos.core.models.entities.Ccomproba;
import com.cumpleanos.core.models.entities.Ctipocom;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.CtipocomId;
import com.cumpleanos.models.persistence.repository.*;
import com.cumpleanos.models.service.interfaces.ISolicitudCompraImportacionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SolicitudCompraImportacionServiceImpl implements ISolicitudCompraImportacionService {

    private final DfacturaRepository dfacturaRepository;
    private final CcomprobaRepository ccoRepository;
    private final CtipocomRepository ctipocomRepository;

    @Transactional
    @Override
    public SolicitudCompraImportacionDTO getSolicitudComproImportacion(BigInteger cco) {

        Long empresa;
        //Cabecera Comprobane principal
        Ccomproba ccomproba = ccoRepository.findById_Codigo(cco).orElseThrow( () -> new EntityNotFoundException("No se encontro el comprobante"));
        empresa = ccomproba.getId().getEmpresa();

        //Sigla del comprobante
        CtipocomId id = new CtipocomId();
        id.setEmpresa(empresa);
        id.setCodigo(ccomproba.getCcoSigla());
        Ctipocom sigla = ctipocomRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("No se encontro el tipocom"));

        //Detalle del comprobante
        Set<Dfactura> listaItems = dfacturaRepository.findById_CcoOrderById_Secuencia(cco);
        Set<DfacturaDTO> itemsDTO = getDfacturaDTOS(listaItems);

        return SolicitudCompraImportacionDTO.builder()
                .cco(cco)
                .almacen(ccomproba.getAlmacen().getNombre())
                .almacenId(ccomproba.getAlmacen().getAlmId())
                .fecha(ccomproba.getCcoFecha())
                .sigla(sigla.getCtiId())
                .docuento(sigla.getNombre())
                .concepto(ccomproba.getCcoConcepto())
                .items(itemsDTO)
                .build();
    }

    /**
     * Convertir clase a dto
     * @param listaItems clases de entidad
     * @return lista de items
     */
    private static Set<DfacturaDTO> getDfacturaDTOS(Set<Dfactura> listaItems) {
        Set<DfacturaDTO> itemsDTO= new LinkedHashSet<>();
        for (Dfactura item : listaItems) {
            DfacturaDTO detalle = new DfacturaDTO(
                    item.getId().getEmpresa(),
                    item.getId().getCco(),
                    item.getId().getSecuencia(),
                    item.getProducto().getProId(),
                    item.getProducto().getNombre(),
                    item.getProducto().getProId1(),
                    item.getCantidad(),
                    item.getPrecio(),
                    item.getTotal()
            );
            itemsDTO.add(detalle);
        }
        return itemsDTO;
    }
}

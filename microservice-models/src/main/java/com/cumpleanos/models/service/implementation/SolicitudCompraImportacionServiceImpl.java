package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.dto.ClienteDTO;
import com.cumpleanos.core.models.dto.DfacturaDTO;
import com.cumpleanos.core.models.dto.SolicitudCompraImportacionDTO;
import com.cumpleanos.core.models.entities.Ccomproba;
import com.cumpleanos.core.models.entities.Cliente;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SolicitudCompraImportacionServiceImpl implements ISolicitudCompraImportacionService {

    private final DfacturaRepository dfacturaRepository;
    private final CcomprobaRepository ccoRepository;
    private final CtipocomRepository ctipocomRepository;

    @Transactional
    @Override
    public SolicitudCompraImportacionDTO getSolicitudComproImportacion(BigInteger cco) {

        // Cabecera Comprobante principal
        Ccomproba ccomproba = ccoRepository.findById_Codigo(cco)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el comprobante"));
        Long empresa = ccomproba.getId().getEmpresa();

        //Sigla del comprobante
        CtipocomId id = new CtipocomId();
        id.setEmpresa(empresa);
        id.setCodigo(ccomproba.getCcoSigla());
        Ctipocom sigla = ctipocomRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("No se encontro el tipocom"));

        // Detalle del comprobante
        Set<Dfactura> listaItems = dfacturaRepository.findById_CcoOrderById_Secuencia(cco);
        Set<DfacturaDTO> itemsDTO = listaItems.stream()
                .map(item -> new DfacturaDTO(
                        item.getId().getEmpresa(),
                        item.getId().getCco(),
                        item.getId().getSecuencia(),
                        item.getProducto().getProId(),
                        item.getProducto().getNombre(),
                        item.getProducto().getProId1(),
                        item.getCantidad(),
                        item.getPrecio(),
                        item.getTotal()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        String comprobante = ccoRepository.getComprobante(empresa, cco);

        // Uso de Optional para evitar múltiples llamadas a getCliente()
        Cliente cliente = ccomproba.getCliente();
        String telefono = null;
        ClienteDTO clienteDTO = null;

        if (cliente != null) {
            telefono = Optional.ofNullable(cliente.getTelefono1())
                    .orElse(Optional.ofNullable(cliente.getTelefono2())
                            .orElse(Optional.ofNullable(cliente.getTelefono3())
                                    .orElse("000000")));

            clienteDTO = new ClienteDTO(
                    cliente.getCliId(),
                    cliente.getNombre(),
                    cliente.getRucCedula(),
                    cliente.getDireccion(),
                    telefono,
                    cliente.getMail()
            );
        }

        return SolicitudCompraImportacionDTO.builder()
                .cco(cco)
                .almacen(Optional.ofNullable(ccomproba.getAlmacen().getNombre()).orElse("Sin almacén"))
                .almacenId(Optional.ofNullable(ccomproba.getAlmacen().getAlmId()).orElse("00"))
                .fecha(ccomproba.getCcoFecha())
                .sigla(sigla.getCtiId())
                .documento(sigla.getNombre())
                .concepto(ccomproba.getCcoConcepto())
                .comprobante(comprobante)
                .items(itemsDTO)
                .cliente(clienteDTO)
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

package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.dto.ClienteDTO;
import com.cumpleanos.core.models.dto.DfacturaDTO;
import com.cumpleanos.core.models.dto.ComprobanteDetalleProductoDTO;
import com.cumpleanos.core.models.entities.Ccomproba;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Ctipocom;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.CtipocomId;
import com.cumpleanos.models.persistence.repository.*;
import com.cumpleanos.models.service.interfaces.IComprobanteDetalleProductoService;
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
public class ComprobanteDetalleProductoServiceImpl implements IComprobanteDetalleProductoService {

    private final DfacturaRepository dfacturaRepository;
    private final CcomprobaRepository ccoRepository;
    private final CtipocomRepository ctipocomRepository;

    @Transactional
    @Override
    public ComprobanteDetalleProductoDTO getComprobanteDetalleProducto(BigInteger cco) {

        // Cabecera Comprobante principal
        Ccomproba ccomproba = ccoRepository.findById_Codigo(cco)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el comprobante"));
        Long empresa = ccomproba.getId().getEmpresa();

        //Sigla del comprobante
        CtipocomId id = new CtipocomId();
        id.setEmpresa(empresa);
        id.setCodigo(ccomproba.getCcoSigla());
        Ctipocom sigla = ctipocomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el tipocom"));

        // Detalle del comprobante
        Set<Dfactura> listaItems = dfacturaRepository.findById_CcoOrderById_Secuencia(cco);
        Set<DfacturaDTO> itemsDTO = listaItems.stream()
                .map(item -> new DfacturaDTO(
                        item.getId().getEmpresa(),
                        item.getId().getCco(),
                        item.getId().getSecuencia(),
                        item.getProducto() != null ? item.getProducto().getProId() : item.getProductoTemp().getProId(),
                        item.getProducto() != null ? item.getProducto().getNombre() : item.getProductoTemp().getNombre(),
                        item.getProducto() != null ? item.getProducto().getProId1() : item.getProductoTemp().getProId(),
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

        return ComprobanteDetalleProductoDTO.builder()
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
}

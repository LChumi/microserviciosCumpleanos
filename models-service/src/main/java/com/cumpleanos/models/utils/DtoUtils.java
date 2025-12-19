package com.cumpleanos.models.utils;

import com.cumpleanos.common.records.DfacturaDTO;
import com.cumpleanos.core.models.entities.Dfactura;

public class DtoUtils {

    public static DfacturaDTO getDfacturaDTO(Dfactura item) {
        return new DfacturaDTO(
                item.getId().getEmpresa(),
                item.getId().getCco(),
                item.getId().getSecuencia(),
                item.getCcoFecha(),
                item.getDfacProducto(),
                item.getProducto() != null ? item.getProducto().getProId() : item.getProductoTemp().getProId(),
                item.getProducto() != null ? item.getProducto().getNombre() : item.getProductoTemp().getNombre(),
                item.getProducto() != null ? item.getProducto().getProId1() : item.getProductoTemp().getProId(),
                item.getCantidad(),
                item.getPrecio(),
                item.getTotal()
        );
    }
}
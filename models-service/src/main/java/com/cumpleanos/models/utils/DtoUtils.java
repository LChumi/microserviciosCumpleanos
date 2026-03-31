package com.cumpleanos.models.utils;

import com.cumpleanos.common.records.DfacturaDTO;
import com.cumpleanos.common.records.ImporItemDTO;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.entities.Imporitem;

public final class DtoUtils {

    private DtoUtils() {
        throw new UnsupportedOperationException("Clase de utilidades, no instanciable");
    }

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
                item.getCanapr(),
                item.getPrecio(),
                item.getTotal()
        );
    }

    public static ImporItemDTO getImporItemDTO(Imporitem item) {
        return new ImporItemDTO(
                item.getId().getEmpresa(),
                item.getId().getComproba(),
                item.getIitProducto(),
                item.getProducto() != null ? item.getProducto().getProId() : item.getProductoTemp().getProId(),
                item.getProducto() != null ? item.getProducto().getNombre() : item.getProductoTemp().getNombre(),
                item.getProducto() != null ? item.getProducto().getProId1() : item.getProductoTemp().getProId(),
                item.getCantPedida(),
                item.getCantLlegada(),
                item.getCantLiquidada(),
                item.getCosto(),
                item.getPrecio()
        );
    }

}
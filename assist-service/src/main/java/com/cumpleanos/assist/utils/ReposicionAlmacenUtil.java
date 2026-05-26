package com.cumpleanos.assist.utils;

import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.ids.CreposicionId;
import com.cumpleanos.core.models.ids.DreposicionId;
import com.cumpleanos.core.models.views.FacRevprodWebV;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cumpleanos.core.models.enums.CreposicionTiposEnum.*;

public final class ReposicionAlmacenUtil {

    private ReposicionAlmacenUtil(){
        throw new UnsupportedOperationException("Utility class");
    }

    private static final String OBS = "REVISION ";

    public static Creposicion generarCabeceraRevision(Long empresa, String usuario, Long almacen, Long bodega, String obs) {
        CreposicionId id = new CreposicionId();
        id.setEmpresa(empresa);

        String observacion = OBS + obs;
        if (observacion.length() > 255) {
            observacion = observacion.substring(0, 255);
        }

        Creposicion c = new Creposicion();
        c.setId(id);
        c.setUsuario(usuario);
        c.setObservacion(observacion);
        c.setFecha(LocalDate.now());
        c.setCreaFecha(LocalDateTime.now());
        c.setEstado(ESTADO_APROBAR.getCodigo());
        c.setFinalizado(NO_FINALIZADO.getCodigo());
        c.setAlmacenId(almacen);
        c.setBodegaId(bodega);
        c.setReferencia(obs);
        c.setTipo(TIPO_REVISION_MER.getCodigo());
        return c;
    }

    public static Dreposicion crearDetalle(String usuario, FacRevprodWebV v, Long idCreposicion) {
        DreposicionId id = new DreposicionId();
        id.setEmpresa(v.getEmpresa());

        // Manejo seguro de cantidad
        long cantidad = (v.getCantidad() != null)
                ? v.getCantidad().longValue()
                : (v.getCanapr() != null ? v.getCanapr().longValue() : 0L);

        // Calcular total con protección
        BigDecimal precio = v.getPrecio() != null ? v.getPrecio() : BigDecimal.ZERO;
        BigDecimal total = precio.multiply(BigDecimal.valueOf(cantidad));

        Dreposicion d = new Dreposicion();
        d.setId(id);
        d.setCreposicionId(idCreposicion);
        d.setProductoId(v.getProCodigo());
        d.setCantSol(cantidad);
        d.setCantApr(0L);
        d.setUsuario(usuario);
        d.setPrecio(precio);
        d.setTotal(total);
        return d;
    }

    public static List<FacRevprodWebV> agruparProductos(List<FacRevprodWebV> list){
        Map<Long, FacRevprodWebV> agrupados = new HashMap<>();

        for (FacRevprodWebV item : list) {
            Long proCodigo = item.getProCodigo();

            if (!agrupados.containsKey(proCodigo)){
                agrupados.put(proCodigo, item);
                continue;
            }

            FacRevprodWebV existente = agrupados.get(proCodigo);

            int cantidadActual = existente.getCantidad() != null ? existente.getCantidad() : 0;

            int cantidadNueva = item.getCantidad() != null ? item.getCantidad() : 0;

            existente.setCantidad(cantidadActual + cantidadNueva);
        }
        return new ArrayList<>(agrupados.values());
    }

}
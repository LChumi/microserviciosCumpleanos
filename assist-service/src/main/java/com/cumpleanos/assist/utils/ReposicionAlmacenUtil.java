package com.cumpleanos.assist.utils;

import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.ids.CreposicionId;
import com.cumpleanos.core.models.ids.DreposicionId;
import com.cumpleanos.core.models.views.FacRevprodWebV;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.cumpleanos.core.models.enums.CreposicionTiposEnum.*;

public final class ReposicionAlmacenUtil {

    private ReposicionAlmacenUtil(){
        throw new UnsupportedOperationException("Utility class");
    }

    private static final String OBS = "REVISION ";

    public static Creposicion generarCabeceraRevision(Long empresa, String usuario, Long almacen, Long bodega, String obs) {
        CreposicionId id = new CreposicionId();
        id.setEmpresa(empresa);

        String observacion= OBS + obs;

        Creposicion c = new Creposicion();
        c.setId(id);
        c.setUsuario(usuario);
        c.setObservacion(observacion);
        c.setFecha(LocalDate.now());
        c.setEstado(ESTADO_PROCESO.getCodigo());
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

}

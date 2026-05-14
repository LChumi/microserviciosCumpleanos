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

    private static final String OBS = "WEB";

    public static Creposicion generarCabeceraRevision(Long empresa, String usuario, Long almacen, Long bodega) {
        CreposicionId id = new CreposicionId();
        id.setEmpresa(empresa);

        Creposicion c = new Creposicion();
        c.setId(id);
        c.setUsuario(usuario);
        c.setObservacion(OBS);
        c.setFecha(LocalDate.now());
        c.setEstado(ESTADO_PROCESO.getCodigo());
        c.setFinalizado(NO_FINALIZADO.getCodigo());
        c.setAlmacenId(almacen);
        c.setBodegaId(bodega);
        c.setTipo(TIPO_REVISION_MER.getCodigo());
        return c;
    }

    public static Dreposicion crearDetalle(String usuario, FacRevprodWebV v, Long idCreposicion) {
        DreposicionId id = new DreposicionId();
        id.setEmpresa(v.getEmpresa());

        BigDecimal total = v.getPrecio().multiply(BigDecimal.valueOf(v.getCantidad()));

        Dreposicion d = new Dreposicion();
        d.setId(id);
        d.setCreposicionId(idCreposicion);
        d.setProductoId(v.getProCodigo());
        d.setCantSol((long) v.getCantidad());
        d.setCantApr(0L);
        d.setUsuario(usuario);
        d.setPrecio(v.getPrecio());
        d.setTotal(total);
        return d;
    }
}

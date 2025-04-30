package com.cumpleanos.pos.persistence.api.jep;

public record Notificacion(
        String idtransaccion,
        String estado,
        String mensaje,
        String identificadorsesion,
        String nummensaje,
        String error
) {}
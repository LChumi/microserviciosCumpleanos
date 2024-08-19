package com.cumpleanos.pos.persistence.api.jep;

public record NotificacionJep(
        String idtransaccion,
        String estado,
        String mensaje,
        String identificadorsesion,
        String nummensaje,
        String error
) {}
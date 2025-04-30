package com.cumpleanos.pos.persistence.api.jep;

public record JepRequestQr(
        String nombreUsuario,
        String contrasena,
        String monto,
        String codigoTransaccion,
        String codigoInstitucion,
        String identificacionSocio,
        String ciudad,
        String direccionSucursal,
        String nombreSucursal,
        String identificadorsesion
) {
}

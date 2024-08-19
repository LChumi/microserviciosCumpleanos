package com.cumpleanos.common.records;

public record ClienteDTO(
        String cliId,
        String nombre,
        String cedula,
        String direccion,
        String telefono,
        String mail
) {
}
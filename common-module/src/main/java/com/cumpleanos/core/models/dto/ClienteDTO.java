package com.cumpleanos.core.models.dto;

public record ClienteDTO(
        String cliId,
        String nombre,
        String cedula,
        String direccion,
        String telefono,
        String mail
) {
}
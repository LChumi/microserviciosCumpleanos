package com.cumpleanos.core.models.dto;

public record UsuarioDTO(
        Long id,
        String username,
        String nombre,
        Boolean inactivo
) {
}
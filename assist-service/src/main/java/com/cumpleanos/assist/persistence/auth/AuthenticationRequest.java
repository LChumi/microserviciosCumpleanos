package com.cumpleanos.assist.persistence.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "El nombre de usuario es requerido")
        String nombreUsuario,
        @NotBlank(message = "La contraseña es requerida")
        String clave
){}

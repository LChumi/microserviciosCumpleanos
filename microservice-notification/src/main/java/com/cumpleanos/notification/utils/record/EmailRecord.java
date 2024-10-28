package com.cumpleanos.notification.utils.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailRecord(
        @NotBlank(message = "El destinatario no puede estar vacio")
        @Email(message = "El formato de correo electronico es invalido ")
        String[] toUser,

        @NotBlank(message = "El asunto no puede estar vacio")
        @Size(max = 100 , message = "El asunto no puede exceder 100 caracteres")
        String subject,

        @NotBlank(message = "El mensaje no puede estar vacio")
        String message
) {
}

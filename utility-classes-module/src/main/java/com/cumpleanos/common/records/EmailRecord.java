package com.cumpleanos.common.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EmailRecord(
        @NotEmpty(message = "Debe existir al menos un destinatario")
        String[] toUser,

        @NotBlank(message = "El asunto no puede estar vacio")
        @Size(max = 100 , message = "El asunto no puede exceder 100 caracteres")
        String subject,

        @NotBlank(message = "El mensaje no puede estar vacio")
        String message
) {
}
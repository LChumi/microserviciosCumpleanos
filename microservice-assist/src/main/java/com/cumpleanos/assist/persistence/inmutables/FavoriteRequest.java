package com.cumpleanos.assist.persistence.inmutables;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FavoriteRequest(@NotNull Long idUsuario, @NotNull Long empresa, @NotBlank String path) {
}

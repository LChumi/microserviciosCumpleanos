package com.cumpleanos.core.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;

public record UpdateCcoBodObsRequest(
        @NotNull Long empresa,
        @NotNull BigInteger codigo,
        @NotNull Long bodega,
        @NotNull @Size(min = 1, max = 255) String observacion) {
}

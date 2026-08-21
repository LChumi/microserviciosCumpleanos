package com.cumpleanos.models.persistence.dto;

public record MinMaxUpdateRequest(
        Long codigo,
        Long empresa,
        Long minimo,
        Long maximo) {
}
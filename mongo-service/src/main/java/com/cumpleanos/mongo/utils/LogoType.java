package com.cumpleanos.mongo.utils;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum LogoType {
    PRIMARY(1L),
    SECONDARY(2L);

    private final Long value;

    public static LogoType fromValue(Long value) {
        return Arrays.stream(values())
                .filter(t -> t.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Codigo de logo invalido: "+ value));
    }
}

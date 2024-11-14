package com.cumpleanos.core.models.dto;

import java.util.List;

public record Menu(
        String label,
        String icon,
        List<MenuItem> items
) {}

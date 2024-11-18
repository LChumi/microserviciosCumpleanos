package com.cumpleanos.assist.persistence.dto;

import java.util.List;

public record MenuItem(
        String label,
        String icon,
        String routerLink,
        MenuItem parent,
        List<MenuItem> children
) {}

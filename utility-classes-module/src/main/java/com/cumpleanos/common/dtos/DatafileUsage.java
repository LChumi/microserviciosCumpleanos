package com.cumpleanos.common.dtos;

public record DatafileUsage(
        String tablespaceName,
        String fileName,
        double sizeGb,
        double freeGb,
        double freePercent
) {
}

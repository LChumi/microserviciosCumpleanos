package com.cumpleanos.core.models.dto;

import lombok.Data;

@Data
public class DatafileUsage {
    private String tablespaceName;
    private String fileName;
    private double sizeGb;
    private double freeGb;
    private double freePercent;
}

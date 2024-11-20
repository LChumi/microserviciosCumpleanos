package com.cumpleanos.assist.persistence.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuDTO{
    private String label;
    private String icon;
    private List<MenuItemDTO> items;
}
package com.cumpleanos.assist.persistence.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuItemDTO {
    private String label;
    private String icon;
    private String routerLink;
    private List<MenuItemDTO> items;
}
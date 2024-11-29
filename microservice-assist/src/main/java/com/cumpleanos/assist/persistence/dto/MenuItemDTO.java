package com.cumpleanos.assist.persistence.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuItemDTO {
    private String label;
    private String icon;
    private List<String> routerLink;
    private List<MenuItemDTO> items;
}
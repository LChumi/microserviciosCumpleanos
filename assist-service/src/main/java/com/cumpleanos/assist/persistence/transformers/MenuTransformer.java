package com.cumpleanos.assist.persistence.transformers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuTransformer {
    private String label;
    private String icon;
    private List<MenuItemTransformer> items;
}
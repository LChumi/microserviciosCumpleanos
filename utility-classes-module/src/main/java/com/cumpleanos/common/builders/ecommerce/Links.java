package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Links {
    private List<Collection> self;
    private List<Collection> collection;
}

package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
    private String id;
    private String src;
}

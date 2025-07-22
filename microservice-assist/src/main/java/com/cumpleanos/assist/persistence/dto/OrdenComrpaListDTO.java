package com.cumpleanos.assist.persistence.dto;

import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrdenComrpaListDTO {
    private List<ProductImportTransformer> listWhitSci;
    private List<ProductImportTransformer> listNotSci;
}

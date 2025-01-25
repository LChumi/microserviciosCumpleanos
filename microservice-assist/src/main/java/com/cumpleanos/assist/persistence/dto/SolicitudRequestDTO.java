package com.cumpleanos.assist.persistence.dto;

import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class SolicitudRequestDTO {
    @NotNull
    private Long empresa;
    @NotNull
    private Long tipodoc;
    @NotNull
    private Long almacen;
    @NotNull
    private Long pventa;
    @NotNull
    private Long sigla;
    @NotNull
    private Long proveedor;
    @NotNull
    private Long usuario;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;
    @NotNull
    private Long modulo;
    @NotNull
    private Long bodega;

    private String observacion;

    @NotEmpty
    private List<ProductImportTransformer> items;
}

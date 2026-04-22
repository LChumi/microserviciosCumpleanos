package com.cumpleanos.mongo.persistence.models.products;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "producto_observacion")
@CompoundIndex(name = "producto_unique_bodega_idx", def = "{'item' : 1, 'idBodega': 1}", unique = true)
public class ProductoObservacion implements Comparable<ProductoObservacion> {

    @Id
    private String id;

    private Long idBodega;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;
    private String item;
    private String descripcion;
    private String bulto;
    private String unidad;
    private String cxb;

    private int stock;

    private BigDecimal precio;
    private BigDecimal precioTotal;

    private String usuario;
    private String detalle;
    private String diferencia;

    private ProductoCorreccion correccion;

    @PrePersist
    @PreUpdate
    public void calcularPrecioTotal() {
        if (precio != null) {
            this.precioTotal = precio.multiply(BigDecimal.valueOf(stock));
        }
    }

    @Override
    public int compareTo(ProductoObservacion o) {
        if (o == null || o.getFecha() == null) return -1;
        if (this.fecha == null) return 1;
        return o.getFecha().compareTo(this.fecha); // orden descendente
    }
}
package com.cumpleanos.mongo.persistence.models.products;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "producto_observacion")
public class ProductoObservacion implements Comparable<ProductoObservacion> {

    @Id
    private String id;

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
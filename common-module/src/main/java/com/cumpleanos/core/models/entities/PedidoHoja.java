package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.PedidoHojaId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "PEDIDO_HOJA", schema = "DATA_USR", indexes = {
        @Index(name = "PHO_NIDX001", columnList = "PHO_BODEGUERO, PHO_EMPRESA"),
        @Index(name = "PHO_NIDX003", columnList = "PHO_CODIGO, PHO_CCO_COMPROBA, PHO_EMPRESA, PHO_HOJA"),
        @Index(name = "PHO_NIDX002", columnList = "PHO_USR_BLOQUEA, PHO_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PedidoHoja {

    @EmbeddedId
    private PedidoHojaId id;

    @ColumnDefault("0")
    @Column(name = "PHO_ESTADO")
    private Long estado;

    @Column(name = "PHO_CODIGO")
    private Long codigo;

    @Column(name = "PHO_USR_BLOQUEA", length = 50)
    private String usrBloquea;
}

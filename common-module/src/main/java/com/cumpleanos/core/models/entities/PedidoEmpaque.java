package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.PedidoEmpaqueId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "PEDIDO_EMPAQUE", schema = "DATA_USR")
public class PedidoEmpaque {

    @EmbeddedId
    private PedidoEmpaqueId id;

    @Column(name = "PBD_CCO_EMPRESA")
    private Long empresa;
    
    @Column(name = "PBD_CCO_COMPROBA")
    private BigInteger ccoComproba;

    @Column(name = "PBD_PRODUCTO")
    private Long producto;

    @Column(name = "PBD_EMPAQUE_CAJA", length = 100)
    private Long caja;

    @Column(name = "PBD_BODEGUERO", length = 100)
    private Long bodeguero;

    @Column(name = "PBD_HOJA")
    private Long hoja;

    @Column(name = "PBD_CANTIDAD", precision = 17, scale = 4)
    private Integer cantidad;

    @Column(name = "PBD_CCO_REFERENCIA")
    private BigInteger ccoReferencia;

    @Column(name = "PBD_ESTADO")
    private Boolean estado;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;
}

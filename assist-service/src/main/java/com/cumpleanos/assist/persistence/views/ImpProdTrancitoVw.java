package com.cumpleanos.assist.persistence.views;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Entity
@Immutable
@Table(name = "IMP_PRODTRANCITO_VW")
public class ImpProdTrancitoVw {

    @Id
    @Column(name = "IPT_SECUENCIA")
    private BigInteger id;

    @Column(name = "IPT_EMPRESA")
    private Long empresa;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "IPT_CCO_COMPROBA")
    private BigInteger ccoComproba;

    @Column(name = "IPT_NRO_COMPROBANTE")
    private String nroComprobante;

    @Column(name = "IPT_FECHA")
    private LocalDate fecha;

    @Column(name = "IPT_PROVEEDOR")
    private Long proveedor;

    @Column(name = "IPT_PRO_CODIGO")
    private Long proCodigo;

    @Column(name = "IPT_PRO_ID")
    private String proId;

    @Column(name = "IPT_PRO_ID1")
    private String proId1;

    @Column(name = "IPT_PRO_NOMBRE")
    private String proNombre;

    @Column(name = "IPT_FAC_FLETE")
    private String facFlete;

    @Column(name = "IPT_NRO_GUIA")
    private String nroGuia;

    @Column(name = "IPT_OBSERVACION")
    private String observacion;

    @Column(name = "IPT_NRO_POLIZA")
    private String nroPoliza;

    @Column(name = "IPT_CANT_PEDIDA")
    private Long cantPedida;

    @Column(name = "IPT_CANT_LLEGADA")
    private Long cantLlegada;

    @Column(name = "IPT_FOB")
    private BigDecimal fob;

    @Column(name = "IPT_FOB_TOTAL_PEDIDO")
    private BigDecimal fobTotalPedido;

    @Column(name = "IPT_FOB_TOTAL_APROBADO")
    private BigDecimal fobTotalAprobado;

    @Column(name = "IPT_ESTADO")
    private String estado;

    @Column(name = "IPT_TIPO_DOC")
    private String tipoDoc;
}

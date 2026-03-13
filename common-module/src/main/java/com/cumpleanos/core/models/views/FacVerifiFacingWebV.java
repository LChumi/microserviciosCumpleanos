package com.cumpleanos.core.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Immutable
@Table(name = "FAC_VERIFI_FACING_WEB_V", schema = "DATA_USR")
public class FacVerifiFacingWebV{
    
    @Id
    @Column(name = "RM_CCO_COMPROBA")
    private Long ccoComproba;

    @Column(name = "RM_COMPROBANTE", length = 4000)
    private String comprobante;

    @Column(name = "RM_DOCTRAN", length = 4000)
    private String doctran;

    @Column(name = "RM_RUC_PROVEEDOR", length = 15)
    private String rucProveedor;

    @Column(name = "RM_ID_PROVEEDOR", length = 10)
    private String idProveedor;

    @Column(name = "RM_RUC_CLIENTE", length = 15)
    private String rucCliente;

    @Column(name = "RM_ID_CLIENTE", length = 15)
    private String idCliente;

    @Column(name = "RM_EMPRESA_FACTURA")
    private Long empresaFactura;

    @Column(name = "RM_EMPRESA_COMPRA")
    private Long empresaCompra;

    @Column(name = "RM_CODIGO_PROVEEDOR")
    private Long codigoProveedor;

    @Column(name = "RM_FECHA_FAC")
    private LocalDate fechaFac;

    @Column(name = "RM_CLI_NOMBRE", length = 100)
    private String cliNombre;

    @Column(name = "RM_PROVEEDOR", length = 100)
    private String proveedor;

    @Column(name = "RM_CCO_PREPEDIDO")
    private Long ccoPrepedido;

    @Column(name = "RM_CLI_CODIGO")
    private Long cliCodigo;

    @Column(name = "RM_TOTAL")
    private Long total;

    @Column(name = "RM_BOD_CODIGO")
    private Long bodCodigo;

    @Column(name = "RM_BOD_ID", length = 10)
    private String bodId;

    @Column(name = "RM_BOD_NOMBRE", length = 100)
    private String bodNombre;

    @Column(name = "RM_TIPO", length = 13)
    private String tipo;

    @Column(name = "RM_TIPO_CODIGO")
    private Long tipoCodigo;
}

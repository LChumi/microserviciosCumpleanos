package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.SriDocEleEmiId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SRI_DOC_ELE_EMI")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
       "ccomproba"
})
public class SriDocEleEmi {

    @EmbeddedId
    private SriDocEleEmiId id;

    @Column(name = "SRI_FECHA")
    private LocalDate sriFecha;

    @Size(max = 50)
    @Column(name = "SRI_COMPROBANTE", length = 50)
    private String sriComprobante;

    @Size(max = 50)
    @Column(name = "SRI_SERIE_COMPROBANTE", length = 50)
    private String sriSerieComprobante;

    @Size(max = 50)
    @Column(name = "SRI_RUC_EMISOR", length = 50)
    private String sriRucEmisor;

    @Size(max = 200)
    @Column(name = "SRI_RAZON_SOCIAL_EMISOR", length = 200)
    private String sriRazonSocialEmisor;

    @Column(name = "SRI_FECHA_EMISION")
    private LocalDate sriFechaEmision;

    @Column(name = "SRI_FECHA_AUTORIZACION")
    private LocalDate sriFechaAutorizacion;

    @Size(max = 50)
    @Column(name = "SRI_TIPO_EMISION", length = 50)
    private String sriTipoEmision;

    @Size(max = 50)
    @Column(name = "SRI_IDENTIFICACION_RECEPTOR", length = 50)
    private String sriIdentificacionReceptor;

    @Size(max = 50)
    @Column(name = "SRI_CLAVE_ACCESO", length = 50)
    private String sriClaveAcceso;

    @Column(name = "SRI_IMPORTE_TOTAL", precision = 17, scale = 4)
    private BigDecimal sriImporteTotal;

    @ColumnDefault("0")
    @Column(name = "SRI_REGISTRADO")
    private Boolean sriRegistrado;

    @Size(max = 10)
    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Size(max = 10)
    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "SRI_BAJA")
    private Boolean sriBaja;

    @Column(name = "SRI_FECHA_BAJA")
    private LocalDate sriFechaBaja;

    @Size(max = 50)
    @Column(name = "SRI_FACTURA", length = 50)
    private String sriFactura;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "SRI_CCO_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "SRI_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false),
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba ccomproba;
}
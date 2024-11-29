package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.EmpleadoId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLEADO", indexes = {
        @Index(name = "EMPLEADO_UDX1", columnList = "EMP_ID, EMP_EMPRESA", unique = true),
        @Index(name = "EMPLEADO_UDX2", columnList = "EMP_NOMBRE, EMP_EMPRESA", unique = true),
        @Index(name = "EMPLEADO_NIDX5", columnList = "EMP_DEPARTAMENTO, EMP_EMPRESA"),
        @Index(name = "EMPLEADO_NIDX3", columnList = "EMP_CIUDAD, EMP_EMPRESA"),
        @Index(name = "EMPLEADO_NIDX1", columnList = "EMP_ALMACEN, EMP_EMPRESA"),
        @Index(name = "EMPLEADO_NIDX6", columnList = "EMP_ESTADO, EMP_EMPRESA"),
        @Index(name = "EMPLEADO_NIDX7", columnList = "EMP_ESTCIVIL, EMP_EMPRESA"),
        @Index(name = "EMPLEADO_NIDX9", columnList = "EMP_TITULO, EMP_EMPRESA"),
        @Index(name = "EMPLEADO_NIDX8", columnList = "EMP_NIVEL, EMP_EMPRESA"),
        @Index(name = "EMPLEADO_NIDX10", columnList = "EMP_ZONAIESS, EMP_EMPRESA"),
        @Index(name = "EMPLEADO_NIDX2", columnList = "EMP_CENTRO, EMP_EMPRESA"),
        @Index(name = "EMPLEADO_NIDX4", columnList = "EMP_CIUDADDIR, EMP_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Empleado {

    @EmbeddedId
    private EmpleadoId id;

    @Column(name = "EMP_ID", nullable = false, length = 10)
    private String empId;

    @Column(name = "EMP_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "EMP_INACTIVO")
    private Boolean inactivo;

    @Column(name = "EMP_DETALLE")
    private Boolean detalle;

    @Column(name = "EMP_RUC_CEDULA", length = 20)
    private String rucCedula;

    @Column(name = "EMP_DIRECCION", nullable = false, length = 100)
    private String direccion;

    @Column(name = "EMP_TELEFONO1", length = 20)
    private String telefono1;

    @Column(name = "EMP_TELEFONO2", length = 20)
    private String telefono2;

    @Column(name = "EMP_TELEFONO3", length = 20)
    private String telefono3;

    @Column(name = "EMP_MAIL", length = 500)
    private String mail;

    @Column(name = "EMP_CONTRATO", nullable = false)
    private Long contrato;

    @Column(name = "EMP_SUELDO", precision = 17, scale = 4)
    private BigDecimal sueldo;

    @Column(name = "EMP_CARGO", nullable = false)
    private Long cargo;

    @Column(name = "EMP_SEXO", nullable = false)
    private Boolean sexo = false;

    @Column(name = "EMP_NUMCARGAS")
    private Short numcargas;

    @Column(name = "EMP_CARGSUBESCOL")
    private Short cargsubescol;

    @Column(name = "EMP_BANNOM")
    private Long bannom;

    @Column(name = "EMP_TCUENTA")
    private Boolean tcuenta;

    @Column(name = "EMP_CUENTA", length = 20)
    private String cuenta;

    @Column(name = "EMP_LIBMILITAR", length = 20)
    private String libmilitar;

    @Column(name = "EMP_CODIESS", length = 20)
    private String codiess;

    @Column(name = "EMP_ACTAIESS", length = 100)
    private String actaiess;

    @Column(name = "EMP_LIBAHORRO", length = 20)
    private String libahorro;

    @Column(name = "EMP_FECHANAC", nullable = false)
    private LocalDate fechanac;

    @Column(name = "EMP_FECHAING")
    private LocalDate fechaing;

    @Column(name = "EMP_FECHARET")
    private LocalDate fecharet;

    @Column(name = "EMP_ANTICIPO", precision = 17, scale = 4)
    private BigDecimal anticipo;

    @Column(name = "EMP_TIPOLIC", length = 2)
    private String tipolic;

    @Column(name = "EMP_FECHALIC")
    private LocalDate fechalic;

    @Column(name = "EMP_TIPOCED", length = 1)
    private String tipoced;

    @Column(name = "EMP_COMISION")
    private Boolean comision;

    @Column(name = "EMP_FECHA_VAC")
    private LocalDate fechaVac;

    @Column(name = "EMP_SALIDA")
    private Boolean salida;

    @Column(name = "EMP_INSTRUCCION")
    private Long instruccion;

    @Column(name = "EMP_TELEFONO4", length = 20)
    private String telefono4;

    @Column(name = "EMP_CALCULO_HORA")
    private Boolean calculoHora;

    @Column(name = "EMP_MOVILIZACION")
    private Boolean movilizacion;

    @ColumnDefault("0")
    @Column(name = "EMP_FONDO_RE")
    private Boolean fondoRe;

    @Column(name = "EMP_CAMCENTRO")
    private Boolean camcentro;

    @Column(name = "EMP_MANO_OBRA")
    private Boolean manoObra;

    @Column(name = "EMP_IMP_RENTA")
    private Long impRenta;

    @Column(name = "EMP_USUARIO")
    private Long usuario;

    @Column(name = "EMP_SECCION")
    private Long seccion;

    @ColumnDefault("0")
    @Column(name = "EMP_DECIMO_XIII")
    private Boolean decimoXiii;

    @ColumnDefault("0")
    @Column(name = "EMP_DECIMO_XIV")
    private Boolean decimoXiv;

    @ColumnDefault("0")
    @Column(name = "EMP_EXT_SALUD_CONYUG")
    private Boolean extSaludConyug;

    @Column(name = "EMP_MAIL_EMPRESA", length = 500)
    private String mailEmpresa;

    @Column(name = "EMP_PROV_XIII")
    private Boolean provXiii;

    @Column(name = "EMP_PROV_XIV")
    private Boolean provXiv;
}

package com.cumpleanos.pos.persistence.entity;

import com.cumpleanos.pos.persistence.ids.FinancieraId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FINANCIERA")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Financiera {

    @EmbeddedId
    private FinancieraId id;

    @Column(name = "FIN_ID", length = 20)
    private String finId;

    @Column(name = "FIN_NOMBRE", length = 100)
    private String nombre;

    @Column(name = "FIN_CLIENTE")
    private Long cliente;

    @Column(name = "FIN_API_KEY", length = 50)
    private String apiKey;

    @Column(name = "FIN_API_SECRET", length = 50)
    private String apiSecret;

    @Column(name = "FIN_USUARIO", length = 50)
    private String usuario;

    @Column(name = "FIN_PASSWORD", length = 50)
    private String password;
}

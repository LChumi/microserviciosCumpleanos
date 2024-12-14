package com.cumpleanos.assist.persistence.dto;

import com.cumpleanos.core.models.entities.Cliente;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {

    private Long codigo;
    private Long empresa;
    private String cliId;
    private short tipo;
    private String nombre;
    private String rucCedula;
    private String direccion;
    private String telefono;
    private String telefono2;
    private String telefono3;
    private String mail;
    private short tipoced;

    public static ClienteDTO mapToCliente(Cliente cliente) {
        return ClienteDTO.builder()
                .codigo(cliente.getId().getCodigo())
                .empresa(cliente.getId().getEmpresa())
                .cliId(cliente.getCliId())
                .tipo(cliente.getTipo())
                .nombre(cliente.getNombre())
                .rucCedula(cliente.getRucCedula())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono1())
                .telefono2(cliente.getTelefono2())
                .telefono3(cliente.getTelefono3())
                .mail(cliente.getMail())
                .tipoced(cliente.getTipoced() != null ? cliente.getTipoced() : 0) // Evitar el error de Nullpointer
                .build();
    }

}

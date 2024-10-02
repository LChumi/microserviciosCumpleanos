package com.cumpleanos.models.service;

import core.cumpleanos.models.entities.Cliente;
import core.cumpleanos.models.ids.ClienteId;

import java.util.List;

public interface IClienteService extends GenericService<Cliente, ClienteId>{
    Cliente findByCedulaRucAndEmpresa(String cedula, Long empresa);
    List<Cliente> findByCliId(String id, Long empresa);
}

package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;

import java.util.List;

public interface IClienteService extends GenericService<Cliente, ClienteId>{
    Cliente findByCedulaRucAndEmpresa(String cedula, Long empresa);
    List<Cliente> findByCliId(String id, Long empresa);
}

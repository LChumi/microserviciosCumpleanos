package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IClienteService extends GenericService<Cliente, ClienteId>{
    @Transactional
    Cliente findByCedulaRucAndEmpresa(String cedula, Long empresa);
    @Transactional
    List<Cliente> findByCliId(String id, Long empresa);
}

package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;

import java.util.Set;

public interface IClienteService extends IGenericService<Cliente, ClienteId> {
    Set<Cliente> findByTipoAndEmpresa(Short tipo, Long empresa);
}

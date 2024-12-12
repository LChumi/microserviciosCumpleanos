package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.dto.ClienteDTO;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;

import java.util.Set;

public interface IClienteService extends IGenericService<Cliente, ClienteId> {
    Set<ClienteDTO> findByTipoAndEmpresa(Short tipo, Long empresa);
    Set<ClienteDTO> findByEmpresaTipoAndCategoria(Long empresa, Short tipo , Long categoria);
}

package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import com.cumpleanos.models.persistence.dto.ClienteDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;

public interface IClienteService extends GenericService<Cliente, ClienteId> {

    @Transactional
    Cliente findByCedulaRucAndEmpresa(String cedula, Short tipo, Long empresa);

    @Transactional
    List<Cliente> findByCliId(String id, Long empresa);

    @Transactional
    Cliente save(Cliente cliente);

    Set<ClienteDTO> findByTipoAndEmpresa(Short tipo, Long empresa);
}

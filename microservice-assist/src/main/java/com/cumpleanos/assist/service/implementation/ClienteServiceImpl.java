package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.dto.ClienteDTO;
import com.cumpleanos.assist.persistence.repository.ClienteRepository;
import com.cumpleanos.assist.service.interfaces.IClienteService;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, ClienteId> implements IClienteService {

    private final ClienteRepository repository;

    @Override
    public CrudRepository<Cliente, ClienteId> getRepository() {
        return repository;
    }

    @Override
    public Set<ClienteDTO> findByTipoAndEmpresa(Short tipo, Long empresa) {
        Set<ClienteDTO> clienteDTOS = new HashSet<>();
        Set<Cliente> cli = repository.findById_EmpresaAndTipo(empresa, tipo);
        for (Cliente client : cli) {
            clienteDTOS.add(ClienteDTO.mapToCliente(client));
        }
        return clienteDTOS;
    }

    @Override
    public Set<ClienteDTO> findByEmpresaTipoAndCategoria(Long empresa, Short tipo, Long categoria) {
        Set<ClienteDTO> clienteDTOS = new HashSet<>();
        Set<Cliente> cli = repository.findById_empresaAndTipoAndCliCategoria(empresa, tipo, categoria);
        for (Cliente client : cli) {
            clienteDTOS.add(ClienteDTO.mapToCliente(client));
        }
        return clienteDTOS;
    }


}

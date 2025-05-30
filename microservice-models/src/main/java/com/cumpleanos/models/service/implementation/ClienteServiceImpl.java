package com.cumpleanos.models.service.implementation;

import com.cumpleanos.models.persistence.dto.ClienteDTO;
import com.cumpleanos.models.persistence.repository.ClienteRepository;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import com.cumpleanos.models.service.interfaces.IClienteService;
import com.cumpleanos.models.utils.enums.Sequence;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, ClienteId> implements IClienteService {

    private final ClienteRepository repository;

    @Override
    public CrudRepository<Cliente, ClienteId> getRepository() {
        return repository;
    }

    @Override
    public Cliente findByCedulaRucAndEmpresa(String cedula,Short tipo, Long empresa) {
        return repository.findById_EmpresaAndRucCedulaAndTipo(empresa, cedula, tipo).orElse(null);
    }

    @Override
    public List<Cliente> findByCliId(String id, Long empresa) {
        return StreamSupport.stream(repository.findByCliIdLikeAndId_Empresa(id+'%', empresa)
                .stream()
                .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Cliente save(Cliente entity) {
        Long nuevoCodigo = getNextSequenceValue(Sequence.CLICODIGO);

        ClienteId id = new ClienteId();
        id.setCodigo(nuevoCodigo);
        id.setEmpresa(entity.getId().getEmpresa());
        entity.setId(id);

        return super.save(entity);
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
}

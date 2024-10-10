package com.cumpleanos.models.service.implementation;

import com.cumpleanos.models.persistence.repository.CatClienteRepository;
import com.cumpleanos.models.persistence.repository.ClienteRepository;
import com.cumpleanos.models.persistence.repository.UbicacionRepository;
import com.cumpleanos.core.models.entities.CatCliente;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Ubicacion;
import com.cumpleanos.core.models.ids.CatClienteId;
import com.cumpleanos.core.models.ids.ClienteId;
import com.cumpleanos.core.models.ids.UbicacionId;
import com.cumpleanos.models.service.interfaces.IClienteService;
import com.cumpleanos.models.utils.enums.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, ClienteId> implements IClienteService {

    private final ClienteRepository repository;
    private final CatClienteRepository catRepository;
    private final UbicacionRepository ubicacionRepository;

    @Override
    public CrudRepository<Cliente, ClienteId> getRepository() {
        return repository;
    }

    @Override
    public Cliente findByCedulaRucAndEmpresa(String cedula, Long empresa) {
        return repository.findById_EmpresaAndRucCedulaAndTipo(empresa, cedula, (short) 2).orElse(null);
    }

    @Override
    public List<Cliente> findByCliId(String id, Long empresa) {
        return StreamSupport.stream(repository.findByCliIdLikeAndId_Empresa(id+'%', empresa)
                .stream()
                .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public Cliente save(Cliente entity) {
        Long nuevoCodigo = getNextSequenceValue(Sequence.CLICODIGO);
        System.out.println(nuevoCodigo);

        ClienteId id = new ClienteId();
        id.setCodigo(nuevoCodigo);
        id.setEmpresa(entity.getId().getEmpresa());

        CatClienteId idCat = new CatClienteId();
        idCat.setCodigo(10000251L);
        idCat.setEmpresa(2L);
        CatCliente categoria= catRepository.findById(idCat).orElse(null);

        UbicacionId idUbi= new UbicacionId();
        idUbi.setCodigo(3L);
        idUbi.setEmpresa(2L);
        Ubicacion ubi = ubicacionRepository.findById(idUbi).orElse(null);

        entity.setCiudad(ubi);
        entity.setCatCliente(categoria);

        entity.setId(id);
        return super.save(entity);
    }
}

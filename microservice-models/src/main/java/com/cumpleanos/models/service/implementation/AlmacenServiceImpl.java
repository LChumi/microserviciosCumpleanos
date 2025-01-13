package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.dto.AlmacenDTO;
import com.cumpleanos.core.models.entities.Almacen;
import com.cumpleanos.core.models.ids.AlmacenId;
import com.cumpleanos.models.persistence.repository.AlmacenRepository;
import com.cumpleanos.models.service.interfaces.IAlmacenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AlmacenServiceImpl extends GenericServiceImpl<Almacen, AlmacenId> implements IAlmacenService {
    private final AlmacenRepository repository;

    @Override
    public CrudRepository<Almacen, AlmacenId> getRepository() {
        return repository;
    }

    @Override
    public Set<AlmacenDTO> listByEmpresa(Long empresa) {
        Set<Almacen> almacenSet = repository.findById_Empresa(empresa);
        Set<AlmacenDTO> almacenes = new HashSet<>();
        for (Almacen almacen : almacenSet) {
            almacenes.add(new AlmacenDTO(
                    empresa,
                    almacen.getId().getCodigo(),
                    almacen.getAlmId(),
                    almacen.getNombre(),
                    almacen.getDireccion()
                    ));
        }
        return almacenes;
    }
}

package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Ubicacion;
import com.cumpleanos.core.models.ids.UbicacionId;
import com.cumpleanos.models.persistence.repository.UbicacionRepository;
import com.cumpleanos.models.service.interfaces.IUbicacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UbicacionServiceImpl extends GenericServiceImpl<Ubicacion, UbicacionId> implements IUbicacionService{

    private final UbicacionRepository repository;


    @Override
    public CrudRepository<Ubicacion, UbicacionId> getRepository() {
        return repository;
    }

    @Override
    public List<Ubicacion> findByEmpresaAndNombre(Long empresa, String nombre) {
        String filtro = "%" + nombre + "%";
        return repository.findById_EmpresaAndNombreLikeIgnoreCase(empresa, filtro);
    }
}

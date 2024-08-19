package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.MenuWRepository;
import com.cumpleanos.assist.service.interfaces.IMenuWService;
import com.cumpleanos.core.models.entities.MenuW;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MenuWServiceImpl extends GenericServiceImpl<MenuW, Long> implements IMenuWService {

    private final MenuWRepository repository;

    @Override
    public CrudRepository<MenuW, Long> getRepository() {
        return repository;
    }

    @Override
    public MenuW updateMenu(MenuW menu, Long id) {

        MenuW found = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Menu no encontrado"));
        found.setNombre(menu.getNombre());
        found.setMnwId(menu.getMnwId());
        found.setInactivo(menu.getInactivo());
        found.setIcono(menu.getIcono());
        found.setReporta(menu.getReporta());
        found.setOrden(menu.getOrden());
        return null;
    }
}
package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.dto.MenuDTO;
import com.cumpleanos.core.models.entities.AccesoRol;

import java.util.Set;

public interface IAccesoRolService extends IGenericService<AccesoRol, Long> {
    Set<MenuDTO> obtenerMenusYSubmenus(Long usuario, Long empresa);
}
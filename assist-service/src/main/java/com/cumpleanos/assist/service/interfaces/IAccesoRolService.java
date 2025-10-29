package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.transformers.MenuTransformer;
import com.cumpleanos.core.models.entities.AccesoRol;
import com.cumpleanos.core.models.entities.Sistema;

import java.util.Set;

public interface IAccesoRolService extends IGenericService<AccesoRol, Long> {
    Set<MenuTransformer> obtenerMenusYSubmenus(Long usuario, Long empresa);

    Set<Sistema> getEmpresas(Long usuario);
}
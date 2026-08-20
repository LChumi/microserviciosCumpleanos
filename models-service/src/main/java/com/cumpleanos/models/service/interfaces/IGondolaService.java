package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Gondola;
import com.cumpleanos.core.models.ids.GondolaId;

import java.util.List;

public interface IGondolaService extends GenericService<Gondola, GondolaId>{

    List<Gondola> finByEmpresa(Long empresa);

    List<Gondola> finByEmpresaAndUsuario(Long empresa, Long gonUsuario);
}
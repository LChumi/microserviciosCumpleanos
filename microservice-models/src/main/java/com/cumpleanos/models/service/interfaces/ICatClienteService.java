package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.CatCliente;
import com.cumpleanos.core.models.ids.CatClienteId;

import java.util.Set;

public interface ICatClienteService extends GenericService<CatCliente, CatClienteId>{
    Set<CatCliente> listByEmpresa(Long empresa);
}

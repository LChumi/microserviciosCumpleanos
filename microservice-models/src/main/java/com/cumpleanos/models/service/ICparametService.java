package com.cumpleanos.models.service;

import com.cumpleanos.core.models.entities.Cparamet;
import com.cumpleanos.core.models.ids.CparametId;

public interface ICparametService extends GenericService<Cparamet, CparametId> {

    Cparamet findByValor(Long valor, Long empresa);
}

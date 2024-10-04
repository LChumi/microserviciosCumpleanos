package com.cumpleanos.models.service;

import core.cumpleanos.models.entities.Cparamet;
import core.cumpleanos.models.ids.CparametId;

public interface ICparametService extends GenericService<Cparamet, CparametId> {

    Cparamet findByValor(Long valor, Long empresa);
}

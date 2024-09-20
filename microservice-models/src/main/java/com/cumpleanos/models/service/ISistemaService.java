package com.cumpleanos.models.service;

import core.cumpleanos.models.entities.Sistema;

public interface ISistemaService extends GenericService<Sistema,Long>{
    Sistema findByRuc(String ruc);
}

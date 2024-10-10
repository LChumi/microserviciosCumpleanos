package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Sistema;

public interface ISistemaService extends GenericService<Sistema,Long>{
    Sistema findByRuc(String ruc);
}

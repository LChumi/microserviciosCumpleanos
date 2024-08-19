package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.TipoDoc;

import java.util.Set;

public interface ITipoDocService extends GenericService<TipoDoc, Long>{
    Set<TipoDoc> listAllOrder();
}

package com.cumpleanos.models.service;

import core.cumpleanos.models.entities.SriDocEleEmi;
import core.cumpleanos.models.ids.SriDocEleEmiId;

public interface ISriDocEleEmiService  extends GenericService<SriDocEleEmi, SriDocEleEmiId>{
    SriDocEleEmi findByClaveAcceso(String claveAcceso);
}

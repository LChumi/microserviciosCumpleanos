package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.SriDocEleEmi;
import com.cumpleanos.core.models.ids.SriDocEleEmiId;

public interface ISriDocEleEmiService  extends GenericService<SriDocEleEmi, SriDocEleEmiId>{
    SriDocEleEmi findByClaveAcceso(String claveAcceso);
}

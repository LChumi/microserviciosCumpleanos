package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.core.models.entities.Programa;
import com.cumpleanos.core.models.entities.ProgramaW;

public interface IProgramaWService extends IGenericService<ProgramaW,Long> {
    ProgramaW getProgramaByPath(String path);
}

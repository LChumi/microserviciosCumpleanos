package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Sistema;

import java.util.List;

public interface ISistemaService extends GenericService<Sistema,Long>{

    Sistema findByRuc(String ruc);

    List<Sistema> findByIdEmpresaGrupoAndNotId(Long idEmpresaGrupo,  Long notId);
}

package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Imppartida;
import com.cumpleanos.core.models.ids.ImppartidaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImppartidaRepository extends JpaRepository<Imppartida, ImppartidaId> {

    Imppartida getByIprIdAndId_empresa(String iprId, Long idEmpresa);

}

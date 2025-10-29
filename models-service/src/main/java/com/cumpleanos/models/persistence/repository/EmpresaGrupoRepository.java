package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.EmpresaGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaGrupoRepository extends JpaRepository<EmpresaGrupo, Long> {
}

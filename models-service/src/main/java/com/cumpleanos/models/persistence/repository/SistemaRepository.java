package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.EmpresaGrupo;
import com.cumpleanos.core.models.entities.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema, Long> {

    Optional<Sistema> findByRuc(String ruc);

    List<Sistema> findByEmpresaGrupo_IdAndIdNot(Long empresaGrupo, Long notId);

}

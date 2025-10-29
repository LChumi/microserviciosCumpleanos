package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.UsrBod;
import com.cumpleanos.core.models.ids.UsrbodId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UsrBodRepository extends JpaRepository<UsrBod, UsrbodId> {
    Set<UsrBod> findById_UsuarioAndId_EmpresaOrderByUboDefaultDesc(Long idUsuario, Long idEmpresa);
}

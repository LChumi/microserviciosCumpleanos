package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Gondola;
import com.cumpleanos.core.models.ids.GondolaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GondolaRepository extends JpaRepository<Gondola, GondolaId>{

    List<Gondola> findById_Empresa(Long empresa);

    List<Gondola> findById_EmpresaAndGonUsuario(Long idEmpresa, Long gonUsuario);
}

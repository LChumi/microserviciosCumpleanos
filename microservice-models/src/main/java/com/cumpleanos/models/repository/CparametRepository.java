package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Cparamet;
import core.cumpleanos.models.ids.CparametId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CparametRepository extends JpaRepository<Cparamet, CparametId> {

    Optional<Cparamet> findByValorAndId_Empresa(Long valor, Long empresa);
}

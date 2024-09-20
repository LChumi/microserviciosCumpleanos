package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.SriDocEleEmi;
import core.cumpleanos.models.ids.SriDocEleEmiId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SriDocEleEmiRepository extends JpaRepository<SriDocEleEmi, SriDocEleEmiId> {

    Optional<SriDocEleEmi> findByClaveAcceso(String sriClaveAcceso);
}

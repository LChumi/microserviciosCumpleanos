package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.SriDocEleEmi;
import core.cumpleanos.models.ids.SriDocEleEmiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SriDocEleEmiRepository extends JpaRepository<SriDocEleEmi, SriDocEleEmiId> {

    Optional<SriDocEleEmi> findByClaveAcceso(String sriClaveAcceso);
}

package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.SriDocEleEmi;
import core.cumpleanos.models.ids.SriDocEleEmiId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SriDocEleEmiRepository extends JpaRepository<SriDocEleEmi, SriDocEleEmiId> {

    SriDocEleEmi findBySriClaveAcceso(String sriClaveAcceso);
}

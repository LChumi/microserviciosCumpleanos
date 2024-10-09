package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.SriDocEleEmi;
import com.cumpleanos.core.models.ids.SriDocEleEmiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SriDocEleEmiRepository extends JpaRepository<SriDocEleEmi, SriDocEleEmiId> {

    Optional<SriDocEleEmi> findByClaveAcceso(String sriClaveAcceso);
}

package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.SriDocEleEmi;
import com.cumpleanos.models.models.ids.SriDocEleEmiId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SriDocEleEmiRepository extends JpaRepository<SriDocEleEmi, SriDocEleEmiId> {
}

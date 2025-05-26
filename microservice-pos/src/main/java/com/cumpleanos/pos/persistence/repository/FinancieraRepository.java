package com.cumpleanos.pos.persistence.repository;

import com.cumpleanos.pos.persistence.entity.Financiera;
import com.cumpleanos.pos.persistence.ids.FinancieraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancieraRepository extends JpaRepository<Financiera, FinancieraId> {
}

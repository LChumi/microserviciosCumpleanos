package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Total;
import com.cumpleanos.core.models.ids.TotalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRepository extends JpaRepository<Total, TotalId> {
}

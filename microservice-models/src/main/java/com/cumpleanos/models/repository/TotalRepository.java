package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Total;
import com.cumpleanos.models.models.ids.TotalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalRepository extends JpaRepository<Total, TotalId> {
}

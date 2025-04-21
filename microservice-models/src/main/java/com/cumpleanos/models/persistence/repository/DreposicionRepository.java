package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.ids.DreposicionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DreposicionRepository extends JpaRepository<Dreposicion, DreposicionId> {

    List<Dreposicion> findByCreposicionId(Long creposicionId);
}

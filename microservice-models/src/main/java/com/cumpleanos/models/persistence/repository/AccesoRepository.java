package com.cumpleanos.models.persistence.repository;


import com.cumpleanos.core.models.entities.Acceso;
import com.cumpleanos.core.models.ids.AccesoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, AccesoId> {
}

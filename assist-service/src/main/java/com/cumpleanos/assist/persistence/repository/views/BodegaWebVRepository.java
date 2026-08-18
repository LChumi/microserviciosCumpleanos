package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.core.models.views.BodegaWebV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BodegaWebVRepository extends JpaRepository<BodegaWebV, Long> {

    List<BodegaWebV> findByUsuarioAndEmpresa(Long usuario, Long empresa);
}

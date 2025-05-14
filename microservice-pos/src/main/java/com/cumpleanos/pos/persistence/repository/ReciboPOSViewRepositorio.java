package com.cumpleanos.pos.persistence.repository;

import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReciboPOSViewRepositorio extends JpaRepository<ReciboPOSView, Long> {

    Optional<ReciboPOSView> findByAlmacenAndPventa(Long almacen, Long pventa);

    Optional<ReciboPOSView> findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa);

    Optional<ReciboPOSView> findByReferencia(String referencia);
}

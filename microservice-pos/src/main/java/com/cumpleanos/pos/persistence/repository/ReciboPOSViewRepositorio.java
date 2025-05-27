package com.cumpleanos.pos.persistence.repository;

import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReciboPOSViewRepositorio extends JpaRepository<ReciboPOSView, Long> {

    Optional<ReciboPOSView> findByAlmacenAndPventa(Long almacen, Long pventa);

    Optional<ReciboPOSView> findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa);

    Optional<ReciboPOSView> findByReferencia(String referencia);
}

package com.cumlpeanos.pos.repository;

import com.cumlpeanos.pos.models.entity.ReciboPOSView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ReciboPOSViewRepositorio extends JpaRepository<ReciboPOSView, BigInteger> {

    Optional<ReciboPOSView> findByAlmacenAndPventa(int almacen, int pventa);
    Optional<ReciboPOSView> findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa);
}

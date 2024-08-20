package com.cumlpeanos.pos.repository;

import com.cumlpeanos.pos.models.entity.ReciboPOS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ReciboPOSRepository extends JpaRepository<ReciboPOS, Long> {
    Optional<ReciboPOS> findByIdAndEmpresa(Long id, Long empresa);
    Optional<ReciboPOS> findByCcoComproba(BigInteger ccoComproba);
}

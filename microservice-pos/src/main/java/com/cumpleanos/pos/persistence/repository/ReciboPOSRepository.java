package com.cumlpeanos.pos.persistence.repository;

import com.cumlpeanos.pos.persistence.entity.ReciboPOS;
import com.cumlpeanos.pos.persistence.ids.ReciboPOSId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;

public interface ReciboPOSRepository extends JpaRepository<ReciboPOS, ReciboPOSId> {
    @Query("SELECT r FROM ReciboPOS r WHERE r.id.codigo=:id AND r.id.empresa=:empresa")
    Optional<ReciboPOS> findByIdAndEmpresa(Long id, Long empresa);

    Optional<ReciboPOS> findByCcoComproba(BigInteger ccoComproba);
}

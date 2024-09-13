package com.cumlpeanos.pos.repository;

import com.cumlpeanos.pos.models.entity.ReciboPOS;
import com.cumlpeanos.pos.models.ids.ReciboPOSId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;

public interface ReciboPOSRepository extends JpaRepository<ReciboPOS, ReciboPOSId> {
    @Query("SELECT r FROM ReciboPOS r WHERE r.id.codigo=:id AND r.id.empresa=:empresa")
    Optional<ReciboPOS> findByIdAndEmpresa(Long id, Long empresa);

    Optional<ReciboPOS> findByCcoComproba(BigInteger ccoComproba);
}

package com.cumpleanos.pos.persistence.repository;

import com.cumpleanos.pos.persistence.entity.ReciboPOS;
import com.cumpleanos.pos.persistence.ids.ReciboPOSId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ReciboPOSRepository extends JpaRepository<ReciboPOS, ReciboPOSId> {

    Optional<ReciboPOS> findByCcoComproba(BigInteger ccoComproba);

    Optional<ReciboPOS> findByReferenciaAndId_Empresa(String referencia, Long idEmpresa);
}

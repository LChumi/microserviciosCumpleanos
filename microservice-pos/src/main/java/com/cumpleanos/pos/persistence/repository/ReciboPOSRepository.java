package com.cumpleanos.pos.persistence.repository;

import com.cumpleanos.pos.persistence.entity.ReciboPOS;
import com.cumpleanos.pos.persistence.ids.ReciboPOSId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Optional;

public interface ReciboPOSRepository extends JpaRepository<ReciboPOS, ReciboPOSId> {
    @Query("SELECT r FROM ReciboPOS r WHERE r.id.codigo=:id AND r.id.empresa=:empresa")
    Optional<ReciboPOS> findByIdAndEmpresa(Long id, Long empresa);

    Optional<ReciboPOS> findByCcoComproba(BigInteger ccoComproba);

    @Modifying
    @Query("UPDATE ReciboPOS r SET r.nroDoc = :nroDoc, r.referencia = :referencia, r.hora = :hora, r.fecha = :fecha " +
            "WHERE r.id.codigo = :codigo AND r.id.empresa = :empresa")
    int actualizarReciboPOS(@Param("nroDoc") String nroDoc,
                            @Param("referencia") String referencia,
                            @Param("hora") String hora,
                            @Param("fecha") String fecha,
                            @Param("codigo") Long codigo,
                            @Param("empresa") Long empresa);
}

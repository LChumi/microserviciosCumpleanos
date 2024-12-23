package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.assist.persistence.entity.ProductoTemp;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductoTempRepository extends JpaRepository<ProductoTemp, Long> {
    Optional<ProductoTemp> findByProIdAndEmpresa(String proId, Long empresa);
    Optional<ProductoTemp> findByCodFabricaAndEmpresa(String proId, Long empresa);

    @Query("SELECT p FROM ProductoTemp p WHERE p.empresa = :empresa AND (p.codFabrica LIKE %:data% OR p.proId LIKE %:data%)")
    Optional<ProductoTemp> findByEmpresaAndCodFabricaOrProId(@Param("empresa") Long empresa, @Param("data") String data);
}

package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.assist.persistence.entity.ProductoTemp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoTempRepository extends JpaRepository<ProductoTemp, Long> {
    Optional<ProductoTemp> findByProIdAndEmpresa(String proId, Long empresa);
}

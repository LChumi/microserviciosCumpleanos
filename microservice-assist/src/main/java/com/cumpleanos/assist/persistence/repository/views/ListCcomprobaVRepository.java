package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.assist.persistence.views.ListCcomprobaV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

public interface ListCcomprobaVRepository extends JpaRepository<ListCcomprobaV, BigInteger> {
    Set<ListCcomprobaV> findByCcoPeriodo(Short periodo);
    Set<ListCcomprobaV> findByFecha(LocalDate fecha);
    Set<ListCcomprobaV> findByMes(Short mes);
    Set<ListCcomprobaV> findByAlmacen(Long ccoAlmacen);
    Set<ListCcomprobaV> findByCcoSerie(Long ccoSerie);
    Set<ListCcomprobaV> findByCcoNumero(Long numero);
}

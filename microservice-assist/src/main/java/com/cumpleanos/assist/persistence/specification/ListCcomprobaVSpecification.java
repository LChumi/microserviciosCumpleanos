package com.cumpleanos.assist.persistence.specification;

import com.cumpleanos.assist.persistence.views.ListCcomprobaV;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListCcomprobaVSpecification {

    public static Specification<ListCcomprobaV> filterBy(
            Long empresa,
            Short periodo,
            LocalDate fecha,
            Short mes,
            Long sigla,
            Long almacen,
            Long serie,
            Long numero,
            String concepto,
            String doctran,
            Long estado,
            Long tipodoc
            ) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (empresa != null) {
                predicates.add(criteriaBuilder.equal(root.get("empresa"), empresa));
            }
            if (periodo != null) {
                predicates.add(criteriaBuilder.equal(root.get("periodo"), periodo));
            }
            if (fecha != null) {
                predicates.add(criteriaBuilder.equal(root.get("fecha"), fecha));  
            }
            if (mes != null) {
                predicates.add(criteriaBuilder.equal(root.get("mes"), mes));  
            }
            if (sigla != null) {
                predicates.add(criteriaBuilder.equal(root.get("sigla"), sigla));
            }
            if (almacen != null) {
                predicates.add(criteriaBuilder.equal(root.get("almacen"), almacen));  
            }
            if (serie != null) {
                predicates.add(criteriaBuilder.equal(root.get("serie"), serie));
            }
            if (numero != null) {
                predicates.add(criteriaBuilder.equal(root.get("numero"), numero));
            }
            if (concepto != null) {
                predicates.add(criteriaBuilder.equal(root.get("concepto"), concepto));
            }
            if (doctran != null) {
                predicates.add(criteriaBuilder.equal(root.get("doctran"), doctran));
            }
            if (estado != null) {
                predicates.add(criteriaBuilder.equal(root.get("estado"), estado));
            }
            if (tipodoc != null) {
                predicates.add(criteriaBuilder.equal(root.get("tipodoc"), tipodoc));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
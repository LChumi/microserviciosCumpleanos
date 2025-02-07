package com.cumpleanos.assist.persistence.specification;

import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImpProdTrancitoVwSpecification {

    public static Specification<ImpProdTrancitoVw> filterBy(
            Long empresa,
            String nroComprobante,
            String observacion,
            Long proveedor,
            LocalDate fecha,
            String estado
    ){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (empresa != null) {
                predicates.add(criteriaBuilder.equal(root.get("empresa"), empresa));
            }
            if (nroComprobante != null){
                String pattern = "%" + nroComprobante + "%";
                predicates.add(criteriaBuilder.like(root.get("nroComprobante"), pattern));
            }
            if (observacion != null){
                String pattern = "%" + observacion + "%";
                predicates.add(criteriaBuilder.like(root.get("observacion"), pattern));
            }
            if (proveedor != null){
                predicates.add(criteriaBuilder.equal(root.get("proveedor"), proveedor));
            }
            if (fecha != null) {
                predicates.add(criteriaBuilder.equal(root.get("fecha"), fecha));
            }
            if (estado != null){
                predicates.add(criteriaBuilder.equal(root.get("estado"), estado));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

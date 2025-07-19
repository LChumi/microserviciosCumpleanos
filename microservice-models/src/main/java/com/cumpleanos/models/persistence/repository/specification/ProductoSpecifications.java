package com.cumpleanos.models.persistence.repository.specification;

import com.cumpleanos.core.models.entities.Producto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProductoSpecifications {

    public static Specification<Producto> matchByEmpresaAndProIdOrProId1(Long empresa, String barra, String item) {
        return (root, query, cb) -> {
            Predicate predEmpresa = cb.equal(root.get("empresa"), empresa);

            Predicate predProdId = cb.equal(root.get("proId"), barra);
            Predicate predProdId1 = cb.equal(root.get("proId1"), item);

            Predicate orPredicate = cb.or(predProdId, predProdId1);

            return cb.and(predEmpresa, orPredicate);
        };
    }
}

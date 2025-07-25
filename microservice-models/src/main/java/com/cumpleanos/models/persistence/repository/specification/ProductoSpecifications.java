package com.cumpleanos.models.persistence.repository.specification;

import com.cumpleanos.core.models.entities.Producto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProductoSpecifications {

    /**
     * Construye una Specification para buscar productos que coincidan con la empresa
     * y (el código de barra OR el nombre del ítem).
     *
     * <p>Este método permite realizar búsquedas flexibles dentro del catálogo de productos
     * de una empresa específica, comparando por campos alternativos.</p>
     *
     * @param empresa ID de la empresa que filtra el contexto.
     * @param barra   Código de barra del producto a buscar.
     * @param item    Nombre del ítem alternativo del producto.
     * @return Specification que aplica la lógica de filtro OR sobre proId y proId1.
     */

    public static Specification<Producto> matchByEmpresaAndProIdOrProId1(Long empresa, String barra, String item) {
        return (root, query, cb) -> {
            Predicate predEmpresa = cb.equal(root.get("id").get("empresa"), empresa);

            Predicate predProdId = cb.equal(root.get("proId"), barra);
            Predicate predProdId1 = cb.equal(root.get("proId1"), item);

            Predicate orPredicate = cb.or(predProdId, predProdId1);

            return cb.and(predEmpresa, orPredicate);
        };
    }
}

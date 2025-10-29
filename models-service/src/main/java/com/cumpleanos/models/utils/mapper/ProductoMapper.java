package com.cumpleanos.models.utils.mapper;

import com.cumpleanos.common.builders.ProductoBuilder;
import com.cumpleanos.core.models.entities.Producto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    /**
     * Actualiza los campos no nulos del dto a la entidad
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromBuilder(ProductoBuilder dto, @MappingTarget Producto entity);
}

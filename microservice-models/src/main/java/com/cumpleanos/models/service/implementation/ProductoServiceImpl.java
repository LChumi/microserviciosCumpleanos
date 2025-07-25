package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;
import com.cumpleanos.models.persistence.repository.ProductoRepository;
import com.cumpleanos.models.service.interfaces.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.cumpleanos.models.persistence.repository.specification.ProductoSpecifications.matchByEmpresaAndProIdOrProId1;
import static com.cumpleanos.models.utils.stringsUtils.normalizedItemsPrefix;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductoServiceImpl extends GenericServiceImpl<Producto, ProductoId> implements IProductoService {

    private final ProductoRepository repository;

    @Override
    public CrudRepository<Producto, ProductoId> getRepository() {
        return repository;
    }

    @Override
    public ProductoDTO getProductoByBarraAndEmpresa(String barra, Long empresa) {
        Producto prod = repository.findById_EmpresaAndProId(empresa, barra).orElse(null);
        if (prod == null) {
            return null;
        } else {
            return new ProductoDTO(
                    prod.getId().getCodigo(),
                    prod.getId().getEmpresa(),
                    prod.getProId(),
                    prod.getNombre(),
                    prod.getUbicacion(),
                    prod.getProId1(),
                    prod.getCliente(),
                    prod.getProveedor().getId().getCodigo(),
                    prod.getGproducto().getId().getCodigo(),
                    prod.getUnidad().getId().getCodigo()
            );
        }
    }

    /**
     * Metodo para poder coicidencias de un producto de una empresa por su barra e item
     * @return la novedad del producto si tuvo coincidencias del mismo y en donde
     */
    @Override
    public String getMatches(Long empresa, String barcode, String item) {

        String novedad = "";

        //Lista de coincidencias muestra los productos por la barra o el item
        List<Producto> coincidences = repository.findAll(
                matchByEmpresaAndProIdOrProId1(empresa, barcode, item)
        );

        //Obtengo la coincidencia de producto que tenga el mismo barcode
        Optional<Producto> productProId = coincidences.stream()
                .filter(p -> p.getProId().equals(barcode))
                .findFirst();

        //Obtengo la coincidencia del producto q tenga el mismo item
        Optional<Producto> productProId1 = coincidences.stream()
                .filter(p -> p.getProId1().equals(item))
                .findFirst();

        boolean barcodeExists = productProId.isPresent();
        boolean itemExists = productProId1.isPresent();
        boolean itemExistDiferetnPrefix = coincidences.stream()
                .anyMatch(p -> normalizedItemsPrefix(p.getProId1()).equals(normalizedItemsPrefix(item)));

        //If anidado con la informacion de las novedades encontradas por las coincidencias
        if (barcodeExists && itemExists) {
            novedad = "REPOSICION";
        } else if (itemExistDiferetnPrefix) {
            novedad = "ITEM CAMBIA DE PREFIJO (REPOSICION)";
        } else if (!barcodeExists && itemExists) {
            String productName = productProId1.map(Producto::getNombre).orElse("NOMBRE NO DISPONIBLE");
            novedad = "ITEM EXISTE CON OTRA BARRA: " + productName;
        } else if (barcodeExists && !itemExists) {
            String productName = productProId.map(Producto::getNombre).orElse("NOMBRE NO DISPONIBLE");
            novedad = "LA BARRA SE ENCUETRA REGISTRADA EN OTRO ITEM: " + productName;
        } else {
            novedad = "PRODUCTO NUEVO";
        }

        return novedad;

    }
}


package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.builders.ProductoBuilder;
import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;
import com.cumpleanos.models.service.interfaces.IProductoService;
import com.cumpleanos.models.utils.mapper.ProductoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
public class ProductoController {

    private final IProductoService service;
    private final ProductoMapper mapper;

    @GetMapping("/producto/barra/{barra}/empresa/{empresa}")
    public ResponseEntity<ProductoDTO> getProductoByBarra(@PathVariable("barra") String barra,
                                                          @PathVariable("empresa") Long empresa) {
        ProductoDTO producto = service.getProductoByBarraAndEmpresa(barra, empresa);

        return ResponseEntity.ok(producto);
    }

    @GetMapping("/producto/id/{codigo}/{empresa}")
    public ResponseEntity<ProductoBuilder> getProductoById(@PathVariable("codigo") Long codigo,
                                                           @PathVariable("empresa") Long empresa) {
        ProductoId id = new ProductoId();
        id.setEmpresa(empresa);
        id.setCodigo(codigo);

        Producto producto = service.findById(id);

        return ResponseEntity.ok(ProductoBuilder.builder()
                .codigo(producto.getId().getCodigo())
                .empresa(producto.getId().getEmpresa())
                .proId1(producto.getProId1())
                .proId(producto.getProId())
                .nombre(producto.getNombre())
                .precio1(producto.getPrecio1())
                .impuesto(producto.getImpuesto())
                .cargaWeb(producto.getCargaWeb())
                .proveedorId(producto.getProveedorId())
                .build()
        );
    }

    @PutMapping("/producto/update")
    public ResponseEntity<ProductoDTO> saveProducto(@RequestBody ProductoBuilder dto) {
        if (dto == null || dto.getCodigo() == null) {
            return ResponseEntity.badRequest().build();
        }

        ProductoId id = new ProductoId();
        id.setCodigo(dto.getCodigo());
        id.setEmpresa(dto.getEmpresa());

        Producto entity = service.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        mapper.updateProductFromBuilder(dto, entity);
        Producto updated = service.save(entity);

        return ResponseEntity.ok(new ProductoDTO(
                updated.getId().getCodigo(),
                updated.getId().getEmpresa(),
                updated.getProId(),
                updated.getNombre(),
                updated.getUbicacion(),
                updated.getProId1(),
                updated.getCliente(),
                updated.getProveedorId(),
                updated.getGProductoId(),
                null
        ));
    }

}

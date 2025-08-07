package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.builders.ProductoBuilder;
import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;
import com.cumpleanos.models.service.interfaces.IProductoService;
import com.cumpleanos.models.utils.mapper.ProductoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Producto", description = "Documentacion API de Productos")
public class ProductoController {

    private final IProductoService service;
    private final ProductoMapper mapper;

    @Operation(summary = "Obtener Producto", tags = {"Producto"}, description = "Obtiene el procucto por la barra y la empresa", responses = {
            @ApiResponse(responseCode = "200", description = "Dto Producto")
    })
    @Parameters({
            @Parameter(name = "barra", description = "Barra de prodcuto"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/producto/barra/{barra}/empresa/{empresa}")
    public ResponseEntity<ProductoDTO> getProductoByBarra(@PathVariable("barra") String barra,
                                                          @PathVariable("empresa") Long empresa) {
        ProductoDTO producto = service.getProductoByBarraAndEmpresa(barra, empresa);

        return ResponseEntity.ok(producto);
    }

    @Operation(summary = "Obtener Producto", tags = {"Producto"}, description = "Obtiene el producto por codigo y empresa", responses = {
            @ApiResponse(responseCode = "200", description = "Producto Builder")
    })
    @Parameters({
            @Parameter(name = "codigo", description = "Codigo de producto"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
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

    @Operation(summary = "Actualizar el producto", tags = {"Producto"}, description = "Actualiza el producto cuando se sube al ecommerce", responses = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado")
    })
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

    @Operation(summary = "Obtener coincidencias", tags = {"Producto"}, description = "Obtiene las coincidencias de productos cuando la barra o el item ya existen", responses = {
            @ApiResponse(responseCode = "200", description = "Registro nuevo , reposicion, novedad")
    })
    @Parameters({
            @Parameter(name = "empresa", description = "Codigo empresa"),
            @Parameter(name = "barcode", description = "Barra del producto"),
            @Parameter(name = "item", description = "Item del producto")
    })
    @GetMapping("/producto/matches/{empresa}")
    public ResponseEntity<String> getMatches(@PathVariable("empresa") Long empresa,
                                             @RequestParam("barcode") String barcode,
                                             @RequestParam("item") String item) {
        String novedad = service.getMatches(empresa, barcode, item);

        if (novedad == null || novedad.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(novedad);
    }
}

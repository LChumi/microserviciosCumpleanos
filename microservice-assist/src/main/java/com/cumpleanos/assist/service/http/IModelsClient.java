package com.cumpleanos.assist.service.http;

import com.cumpleanos.common.builders.ProductoBuilder;
import com.cumpleanos.common.dtos.BodegaDTO;
import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.common.records.*;
import com.cumpleanos.core.models.entities.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name = "models-service")
public interface IModelsClient {

    //TODO servicio que viene del controlador UsuarioController
    @GetMapping("/models/usuario/{userId}")
    ResponseEntity<Usuario> findByUsrId(@PathVariable String userId);

    @GetMapping("/models/usuario/{userId}/{clave}")
    ResponseEntity<Usuario> findByUsrClave(@PathVariable String userId, @PathVariable String clave);

    @GetMapping("/models/usuario/codigo/{codigo}")
    ResponseEntity<Usuario> findByUsrCodigo(@PathVariable Long codigo);

    //TODO servicio que viene del controlador EmpleadoController
    @GetMapping("/models/empleado/id-usuario/{usuarioId}")
    ResponseEntity<Empleado> getEmpleadoByUsuarioId(@PathVariable Long usuarioId);

    //TODO servicio que viene del controlador PuntoVentaController
    @GetMapping("/models/pve/listar/{empresa}/{almacen}")
    ResponseEntity<Set<PuntoVentaDTO>> listarPve(@PathVariable Long empresa, @PathVariable Long almacen);

    //TODO servicio que viene del controlador DtipoDocController
    @GetMapping("/models/dtipodoc/{empresa}/{tpdCodigo}")
    ResponseEntity<DTipoDocDTO> getDtipoDoc(@PathVariable Long empresa, @PathVariable Long tpdCodigo);

    //TODO servicio que viene del controlador AlmacenController
    @GetMapping("/models/almacenes/{empresa}")
    ResponseEntity<Set<AlmacenDTO>> listarAlmacenes(@PathVariable("empresa") Long empresa);

    @GetMapping("/models/almacen-get/{empresa}/{codigo}")
    ResponseEntity<AlmacenDTO> getById(@PathVariable Long empresa, @PathVariable Long codigo);

    //TODO servicio que viene del controlador DfacturaController
    @PostMapping("/models/dfac/new")
    ResponseEntity<Boolean> create(@RequestBody Dfactura dfactura);

    //Todo servicio que viene del controlador ClienteController
    @GetMapping("/models/cliente/ruc/{ruc}/{tipo}/{empresa}")
    ResponseEntity<ClienteRecord> findByRucAndEmpresa(@PathVariable("ruc") String ruc, @PathVariable("tipo") Short tipo, @PathVariable("empresa") Long empresa);

    @PostMapping("/models/cliente/new")
    ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente);

    @GetMapping("/models/clientesid/{cliId}/{empresa}")
    ResponseEntity<List<String>> getClientes(@PathVariable("cliId") String cliId, @PathVariable("empresa") Long empresa);

    //TODO servicio que viene del controlador BodegaController
    @GetMapping("/models/bodega/web/{empresa}")
    ResponseEntity<BodegaDTO> getBodegaWeb(@PathVariable Long empresa);

    //TODO servicio que viene del controlador SistemaController
    @GetMapping("models/id-empresa/{id}")
    ResponseEntity<Sistema> getEmpresaById(@PathVariable Long id);

    @GetMapping("models/list/empresa-grupo/{empresa}/{excludeId}")
    ResponseEntity<List<Sistema>> listByEmpresaGrupoExcludeId(@PathVariable Long empresa, @PathVariable Long excludeId);

    //TODO servicio que viene del controlador FuctionOracleController
    @GetMapping("/models/verificarJuridico/{ruc}")
    ResponseEntity<Long> verificarJuridico(@PathVariable("ruc") String ruc);

    //TODO servicio que viene del controlador UbicacionController
    @GetMapping("/models/ubicacion/{emp}/{nombre}")
    ResponseEntity<List<Ubicacion>> getUbicacionByNombre(@PathVariable Long emp, @PathVariable String nombre);

    //TODO servicio que viene del controlador ProductoController
    @GetMapping("/models/producto/barra/{barra}/empresa/{empresa}")
    ResponseEntity<ProductoDTO> getProductoByBarra(@PathVariable("barra") String barra, @PathVariable("empresa") Long empresa);

    @GetMapping("models/producto/id/{codigo}/{empresa}")
    ResponseEntity<ProductoBuilder> getProductoById(@PathVariable("codigo") Long codigo, @PathVariable("empresa") Long empresa);

    @PutMapping("models/producto/update")
    ResponseEntity<ProductoDTO> updateProducto(@RequestBody ProductoBuilder producto);

    @GetMapping("models/producto/matches/{empresa}")
    ResponseEntity<String> getMatches(@PathVariable("empresa") Long empresa, @RequestParam("barcode") String barcode, @RequestParam("item") String item);

    //ECOMMERCE
    //TODO servicio que viene del controlador DreposicionController
    @PostMapping("/models/save/dreposicion")
    ResponseEntity<Dreposicion> saveDreposicion(@RequestBody Dreposicion dreposicion);

    //TODO servicio que viene del controlador CreposicionController
    @PostMapping("/models/creposicion/save")
    ResponseEntity<Creposicion> saveCreposicion(@RequestBody Creposicion creposicion);

    @GetMapping("/models/creposicion/find/{referencia}/{empresa}")
    ResponseEntity<Boolean> findCreposicionByReferencia(@PathVariable("referencia") String referencia, @PathVariable("empresa") Long empresa);

    //TODO servicio que viene del controlador ReposicionController
    @PostMapping("/models/crear-pago")
    ResponseEntity<ReposicionPago> createPago(@RequestBody ReposicionPago reposicionPago);

    @GetMapping("/models/parametro/{empresa}/{sigla}/{secuencia}/{tipo}")
    ResponseEntity<Long> verificarParametro(@PathVariable("empresa") Long empresa,
                                            @PathVariable("sigla") String sigla,
                                            @PathVariable("secuencia") String secuencia,
                                            @PathVariable("tipo") int tipo);
}

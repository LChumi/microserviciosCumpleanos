package com.cumpleanos.assist.service.http;

import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.common.records.DTipoDocDTO;
import com.cumpleanos.common.records.PuntoVentaDTO;
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

    //TODO servicio que viene del controlador DfacturaController
    @PostMapping("/models/dfac/new")
    ResponseEntity<Boolean> create(@RequestBody Dfactura dfactura);

    //TODO servicio que viene del controlador CreposicionController
    @PostMapping("/models/creposicion/save")
    ResponseEntity<Creposicion> saveCreposicionEc(@RequestBody Creposicion creposicion);

    //Todo servicio que viene del controlador ClienteController
    @GetMapping("/models/cliente/ruc/{ruc}/{tipo}/{empresa}")
    ResponseEntity<ClienteRecord> findByRucAndEmpresa(@PathVariable("ruc") String ruc, @PathVariable("tipo") Short tipo, @PathVariable("empresa") Long empresa);

    @PostMapping("/models/cliente/new")
    ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente);

    @GetMapping("/models/clientesid/{cliId}/{empresa}")
    ResponseEntity<List<String>> getClientes(@PathVariable("cliId") String cliId, @PathVariable("empresa") Long empresa);

    //TODO servicio que viene del controlador FuctionOracleController
    @GetMapping("/models/verificarJuridico/{ruc}")
    ResponseEntity<Long> verificarJuridico(@PathVariable("ruc") String ruc);
}

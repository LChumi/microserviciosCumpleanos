package com.cumpleanos.assist.service.http;

import com.cumpleanos.core.models.dto.AlmacenDTO;
import com.cumpleanos.core.models.dto.DTipoDocDTO;
import com.cumpleanos.core.models.dto.PuntoVentaDTO;
import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.core.models.entities.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}

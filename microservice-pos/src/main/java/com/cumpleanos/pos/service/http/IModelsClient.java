package com.cumpleanos.pos.service.http;

import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.core.models.entities.Sistema;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "models-service")
public interface IModelsClient {

    @GetMapping("/models/id-empresa/{id}")
    ResponseEntity<Sistema> getEmpresaById(@PathVariable Long id);

    @GetMapping("/models/almacen-get/{empresa}/{codigo}")
    ResponseEntity<AlmacenDTO> getAlmacenByempresaAndAlmId(@PathVariable Long empresa, @PathVariable Long codigo);

    @GetMapping("/models/cliente/ced/{empresa}/{clicodigo}")
    ResponseEntity<String> getClienteCedById(@PathVariable("empresa") Long empresa, @PathVariable("clicodigo") Long codigo) ;
}

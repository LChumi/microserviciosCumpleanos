package com.cumpleanos.reccomprobantes.clients;

import core.cumpleanos.models.entities.Cliente;
import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "models-service")
public interface ModelsClient {

    //TODO servicio que viene del controlador SistemaController
    @GetMapping("/models/empresa/{ruc}")
    ResponseEntity<Sistema> findByRuc(@PathVariable("ruc") String ruc);

    //TODO servicio que viene del controlador SriDocEleEmiServiceController
    @PostMapping("/models/sri/crear")
    ResponseEntity<SriDocEleEmi> save(@RequestBody SriDocEleEmi sriDocEleEmi);

    @GetMapping("/models/sri-emitido/{claveAcceso}")
    ResponseEntity<SriDocEleEmi> findByClaveAcceso(@PathVariable("claveAcceso") String claveAcceso);

    @PutMapping("/models/sri/actualizado/")
    ResponseEntity<SriDocEleEmi> updateDocument(@RequestBody SriDocEleEmi sriDocEleEmi);

    //TODO servicio que viene del controlador ClienteController
    @GetMapping("/models/cliente/ruc/{ruc}/{empresa}")
    ResponseEntity<Cliente> findByRucAndEmpresa(@PathVariable("ruc") String ruc, @PathVariable("empresa") Long empresa);

    @PostMapping("/models/cliente/new")
    ResponseEntity<Cliente> save(@RequestBody Cliente cliente);

    @GetMapping("models/clientesid/{cliId}/{empresa}")
    ResponseEntity<List<String>> getClientes(@PathVariable("cliId") String cliId, @PathVariable("empresa") Long empresa);

    //TODO servicio que viene del controlador FuctionOracleController
    @GetMapping("/models/verificarJuridico/{ruc}")
    ResponseEntity<Long> verificarJuridico(@PathVariable("ruc") String ruc);

    @GetMapping("/models/parametro/{empresa}/{sigla}/{secuencia}/{tipo}")
    ResponseEntity<Long> verificarParametro(@PathVariable("empresa") int empresa,
                                            @PathVariable("sigla") String sigla,
                                            @PathVariable("secuencia") String secuencia,
                                            @PathVariable("tipo") int tipo );
}

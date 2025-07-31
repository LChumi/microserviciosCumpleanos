package com.cumpleanos.reccomprobantes.service.http;

import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.core.models.entities.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "models-service")
public interface ModelsClient {

    //TODO servicio que viene del controlador SistemaController
    @GetMapping("/sistema/empresa/{ruc}")
    ResponseEntity<Sistema> findByRuc(@PathVariable("ruc") String ruc);

    //TODO servicio que viene del controlador SriDocEleEmiServiceController
    @PostMapping("/models/sistema/empresa/{ruc}")
    ResponseEntity<SriDocEleEmi> save(@RequestBody SriDocEleEmi sriDocEleEmi);

    @GetMapping("/models/sri-doc/{claveAcceso}")
    ResponseEntity<SriDocEleEmi> findByClaveAcceso(@PathVariable("claveAcceso") String claveAcceso);

    @PutMapping("/models/sri-doc/actualizado/")
    ResponseEntity<SriDocEleEmi> updateDocument(@RequestBody SriDocEleEmi sriDocEleEmi);

    //TODO servicio que viene del controlador ClienteController
    @GetMapping("/models/cliente/ruc/{ruc}/{tipo}/{empresa}")
    ResponseEntity<ClienteRecord> findByRucAndEmpresa(@PathVariable("ruc") String ruc, @PathVariable("tipo") Short tipo, @PathVariable("empresa") Long empresa);

    @PostMapping("/models/cliente/new")
    ResponseEntity<Cliente> save(@RequestBody Cliente cliente);

    @GetMapping("/models/cliente/id/{cliId}/{empresa}")
    ResponseEntity<List<String>> getClientes(@PathVariable("cliId") String cliId, @PathVariable("empresa") Long empresa);

    //TODO servicio que viene del controlador FuctionOracleController
    @GetMapping("/models/function-oralce/verificarJuridico/{ruc}")
    ResponseEntity<Long> verificarJuridico(@PathVariable("ruc") String ruc);

    @GetMapping("/models/function-oracle/parametro/{empresa}/{sigla}/{secuencia}/{tipo}")
    ResponseEntity<Long> verificarParametro(@PathVariable("empresa") Long empresa,
                                            @PathVariable("sigla") String sigla,
                                            @PathVariable("secuencia") String secuencia,
                                            @PathVariable("tipo") int tipo );

    //TODO servicio que viene del controlador AutClienteController
    @GetMapping("/models/autcliente/get/{nroAut}/{empresa}")
    ResponseEntity<Autcliente> getAutCliente(@PathVariable("nroAut") String nroAut, @PathVariable("empresa") Long empresa);

    @PostMapping("/models/autcliente/save")
    ResponseEntity<Autcliente> saveAutCliente(@RequestBody Autcliente autcliente);

    //TODO servicio que viene del controlador CparametController
    @GetMapping("/models/cparamet/get/{empresa}/{codigo}")
    ResponseEntity<Cparamet> getParamet(@PathVariable("empresa") Long empresa, @PathVariable("codigo") Long codigo);


    //TODO servicio que viene del controlador RetDato
    @GetMapping("/models/retdato/{empresa}/{tablacoa}/{id}")
    ResponseEntity<RetDato> getRetDato(@PathVariable("empresa") Long empresa, @PathVariable("tablacoa") Long tablacoa, @PathVariable("id") String id);
}

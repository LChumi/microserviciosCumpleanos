package com.cumpleanos.pos.service.http;

import com.cumpleanos.pos.persistence.api.tramaco.authentication.request.EntradaAutenticarWs;
import com.cumpleanos.pos.persistence.api.tramaco.authentication.response.RespuestaAutenticarWs;
import com.cumpleanos.pos.persistence.api.tramaco.calcularPrecio.request.EntradaCalcularPrecioGuiaWs;
import com.cumpleanos.pos.persistence.api.tramaco.calcularPrecio.response.RespuestaCalcularPrecio;
import com.cumpleanos.pos.persistence.api.tramaco.consultaLocalidad.RespuestaConsultaLocalidad;
import com.cumpleanos.pos.persistence.api.tramaco.consultarUbiGeo.RespuestaubicacionGeo;
import com.cumpleanos.pos.persistence.api.tramaco.generarGiaPdf.EntradaGenerarPdfWs;
import com.cumpleanos.pos.persistence.api.tramaco.generarGiaPdf.RespuestaGenerarGuiaPdf;
import com.cumpleanos.pos.persistence.api.tramaco.generarGuias.request.EntradaGenerarGuiaWs;
import com.cumpleanos.pos.persistence.api.tramaco.generarGuias.response.RespuestaGenerarGuia;
import com.cumpleanos.pos.persistence.api.tramaco.trackingGuias.request.EntradaTrackGuiaWs;
import com.cumpleanos.pos.persistence.api.tramaco.trackingGuias.response.RespuestaTrackingGuias;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "tramacoClient", url ="http://181.39.93.44:8282/dmz-tramaco-comercial-ws/webresources")
public interface tramacoClient {

    @PostMapping("/usuario/autenticar")
    ResponseEntity<RespuestaAutenticarWs> autenticar(
            @RequestHeader("Authorization") String token,
            @RequestBody EntradaAutenticarWs entradaAutenticarWs
    );

    @PostMapping("/guiaTk/calcularPrecio")
    ResponseEntity<RespuestaCalcularPrecio> calcularPrecio(
            @RequestHeader("Authorization") String token,
            @RequestBody EntradaCalcularPrecioGuiaWs entradaCalcularPrecioGuiaWs
            );

    @PostMapping("/guiaTk/generarGuia")
    ResponseEntity<RespuestaGenerarGuia> generarGuia(
            @RequestHeader("Authorization") String token,
            @RequestBody EntradaGenerarGuiaWs entradaGenerarGuiaWs
            );

    @PostMapping("/guiaTk/generarPdf")
    ResponseEntity<RespuestaGenerarGuiaPdf> generarPdf(
            @RequestHeader("Authorization") String token,
            @RequestBody EntradaGenerarPdfWs entradaGenerarPdfWs
            );

    @PostMapping("/guiaTk/consultarTracking")
    ResponseEntity<RespuestaTrackingGuias> consultarTracking(
            @RequestHeader("Authorization") String token,
            @RequestBody EntradaTrackGuiaWs entradaTrackGuiaWs
            );

    @GetMapping("/consultaTk/consultarLocalidadContrato")
    ResponseEntity<RespuestaConsultaLocalidad> consultarLocalidadesContrato(
            @RequestHeader("Authorization") String token
    );

    @GetMapping("/ubicacionGeografica/consultar")
    ResponseEntity<RespuestaubicacionGeo> consultarUbicacionGeografica(
            @RequestHeader("Authorization") String token
    );
}

package com.cumpleanos.pos.persistence.api.tramaco.trackingGuias.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record Transaccion(
         String adjunto,
         String centroCosto,
         String cliente,
         String contenedor,
         String contrato,
         String descripcion,
         String destino,
         String destinoValido,
         String entregadoPor,
         String estado,
         String factura,
         Date fecha,
         String gestorEntrega,
         String guia,
         String hora,
         Double latitud,
         String localidad,
         Double longitud,
         String observacion,
         String origen,
         Long paquetes,
         String parDestino,
         String parOrigen,
         Long pesoCliente,
         Long pesoReal,
         Long pesoValidado,
         String producto,
         String puntoEmision,
         String quienRecibe,
         Long valorAsegurado
){}

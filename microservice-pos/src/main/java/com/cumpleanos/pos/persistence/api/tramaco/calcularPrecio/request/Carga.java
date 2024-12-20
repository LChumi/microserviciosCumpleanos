package com.cumpleanos.pos.persistence.api.tramaco.calcularPrecio.request;

public record Carga(
         String alto,
         String ancho,
         String bultos,
         String cajas,
         String cantidadDoc,
         String contrato,
         String largo,
         String localidad,
         String localidadDestino,
         String peso,
         String producto,
         String valorAsegurado
){}

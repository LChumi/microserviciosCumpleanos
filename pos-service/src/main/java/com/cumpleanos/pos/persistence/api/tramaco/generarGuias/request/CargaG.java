package com.cumpleanos.pos.persistence.api.tramaco.generarGuias.request;

public record CargaG (
         Long localidad,
         String adjuntos,
         String referenciaTercero,
         Long largo,
         String descripcion,
         Long valorCobro,
         Long valorAsegurado,
         Long contrato,
         String peso,
         String observacion,
         String producto,
         String ancho,
         String bultos,
         String cajas,
         String cantidadDoc,
         String alto,
         String guia
){}

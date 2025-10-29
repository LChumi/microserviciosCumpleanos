package com.cumpleanos.pos.persistence.api.tramaco.trackingGuias.response;

import com.cumpleanos.pos.persistence.api.tramaco.CuerpoRespuesta;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record RespuestaTrackingGuias(
         String type,
         CuerpoRespuesta cuerpoRespuesta,
         LstSalidaTrackGuiaW[] lstSalidaTrackGuiaWs,
         Object[] lstServicios,
         Transaccion transaccion,
         Destinatario destinatario,
         Destinatario remitente
){}

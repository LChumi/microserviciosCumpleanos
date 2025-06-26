package com.cumpleanos.pos.service.http;

import com.cumpleanos.pos.persistence.api.datapos.DatosEnvioRequest;
import com.cumpleanos.pos.persistence.api.datapos.DatosRecepcionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiConsumoService {

    @Value("${pos.base-url}")
    private String baseUrl;

    @Value("${pos.puerto}")
    private String puerto;

    private final RestTemplate restTemplate;

    private static final String PROCESAR_PAGO="pos/procesarPago/";
    private static final String ANULAR_PAGO="pos/anular-pago/";
    private static final String ULTIMA_TRANSACCION="pos/ultima-transaccion/";
    private static final String LISTA_PUERTOS_COM="pos/listaPuertosCom";

    private static final String PROCESAR_PAGO_LAN="pos/procesar_pago_lan/";
    private static final String ANULAR_PAGO_LAN="pos/anular_pago_lan/";
    private static final String ULTIMA_TRANSACCION_LAN="pos/ultima_transaccion_lan/";

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
        return headers;
    }

    public DatosRecepcionResponse procesarPago(String ip, String puertoCom, DatosEnvioRequest request){
        String url = String.format("%s%s:%s%s%s", baseUrl, ip, puerto, PROCESAR_PAGO, puertoCom);
        HttpEntity<DatosEnvioRequest> entity = new HttpEntity<>(request, createHeaders());

        try {
            ResponseEntity<DatosRecepcionResponse> response = restTemplate.postForEntity(url, entity, DatosRecepcionResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                log.error("Respuesta no satisfactoria en el Cliente ip:{} en el puerto COM: {} statusCode:{}", ip, puertoCom, response.getStatusCode());
                throw new HttpServerErrorException(response.getStatusCode(), "Error en el servicio respuesta no satisfactoria");
            }
        } catch (HttpServerErrorException e) {
            log.error("ERROR: al enviar la solicitud Procesar Pago al cliente ip:{} en el puerto COM: {} statusCode:{} ", ip, puertoCom, e.getStatusCode());
            throw new HttpServerErrorException(e.getStatusCode(), "Error en el servicio Cliente al Procesar el pago");
        }
    }

    public Map<String, String> listarPuertos(String ip) {
        String url = String.format("%s%s:%s%s", baseUrl, ip, puerto, LISTA_PUERTOS_COM);

        try {
            ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {});
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                log.error("Respuesta no satisfactoria al listar puertos ip:{} statusCode:{}", ip, response.getStatusCode());
                throw new HttpServerErrorException(response.getStatusCode(), "Error en el servicio");
            }
        } catch (HttpServerErrorException e) {
            log.error("ERROR: al listar puertos ip:{} statusCode:{}", ip, e.getStatusCode());
            throw new HttpServerErrorException(e.getStatusCode(), "Error en el servicio Cliente");
        }
    }

    public DatosRecepcionResponse anularPago(String ip, String puertoCom, String numReferencia) {
        String url = String.format("%s%s:%s%s%s?numReferencia=%s", baseUrl, ip, puerto, ANULAR_PAGO, puertoCom,numReferencia);
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<DatosRecepcionResponse> response = restTemplate.postForEntity(url, entity, DatosRecepcionResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                log.error("Respuesta no satisfactoria al anularPago cliente ip:{} en el puerto COM:{} statusCode:{}", ip, puertoCom, response.getStatusCode());
                throw new HttpServerErrorException(response.getStatusCode(), "Error en el servicio Cliente respuesta no satisfactoria");
            }
        } catch (HttpServerErrorException e) {
            log.error("ERROR: al procesar la anulacion de pago al cliente ip:{} en el puerto COM:{} statusCode:{} ", ip, puertoCom, e.getStatusCode());
            throw new HttpServerErrorException(e.getStatusCode(), "Error en el servicio Cliente al anular el pago ");
        }
    }

    public DatosRecepcionResponse obtenerUltimaTransaccion(String ip, String puertoCom) {
        String url = String.format("%s%s:%s%s%s", baseUrl, ip, puerto, ULTIMA_TRANSACCION, puertoCom);

        try {
            ResponseEntity<DatosRecepcionResponse> response = restTemplate.getForEntity(url, DatosRecepcionResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                log.error("Respuesta no satisfactoria en el cliente ip:{} en el puerto COM:{} statusCode:{}", ip, puertoCom, response.getStatusCode());
                throw new HttpServerErrorException(response.getStatusCode(), "Error en el servicio Cliente respuesta no satisfactoria");
            }
        } catch (HttpServerErrorException e) {
            log.error("Error al enviar la solicitud obtenerUltimaTransaccion al cliente ip:{} en el puerto COM:{} statusCode:{} ", ip, puertoCom, e.getStatusCode());
            throw new HttpServerErrorException(e.getStatusCode(), "Error en el servicio Cliente obtenerUltimaTransaccion ");
        }
    }

    /*
    Todo proceso que realiza la conexion LAN a Datafast
     */

    public DatosRecepcionResponse procesarPagoLan(String ip, String puertoDtf, String ipDtf, DatosEnvioRequest request){
        String url = String.format("%s%s:%s%s%s/%s", baseUrl, ip, puerto, PROCESAR_PAGO_LAN, puertoDtf, ipDtf);
        HttpEntity<DatosEnvioRequest> entity = new HttpEntity<>(request, createHeaders());

        try {
            ResponseEntity<DatosRecepcionResponse> response = restTemplate.postForEntity(url, entity, DatosRecepcionResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                log.error("Respuesta no satisfactoria en el Cliente ip:{} en Datafast {}:{} statusCode:{}", ip, ipDtf, puertoDtf, response.getStatusCode());
                throw new HttpServerErrorException(response.getStatusCode(), "Error en el servicio respuesta no satisfactoria");
            }
        } catch (HttpServerErrorException e) {
            log.error("ERROR: al enviar la solicitud Procesar Pago ip:{} en Datafast {}:{} statusCode ", ip, ipDtf, puertoDtf, e);
            throw new HttpServerErrorException(e.getStatusCode(), "Error en el servicio Cliente al Procesar el pago");
        }
    }

    public DatosRecepcionResponse anularPagoLan(String ip, String puertoDtf,String ipDtf, String numReferencia) {
        String url = String.format("%s%s:%s%s%s/%s?numReferencia=%s", baseUrl, ip, puerto, ANULAR_PAGO_LAN, puertoDtf, ipDtf ,numReferencia);
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<DatosRecepcionResponse> response = restTemplate.postForEntity(url, entity, DatosRecepcionResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                log.error("Respuesta no satisfactoria al anular pago en cliente ip:{} en Datafast {}:{} statusCode:{}", ip, ipDtf, puertoDtf, response.getStatusCode());
                throw new HttpServerErrorException(response.getStatusCode(), "Error en el servicio Cliente respuesta no satisfactoria");
            }
        } catch (HttpServerErrorException e) {
            log.error("ERROR: al enviar la solicitud Anular Pago ip:{} en Datafast {}:{} statusCode ", ip, ipDtf, puertoDtf, e);
            throw new HttpServerErrorException(e.getStatusCode(), "Error en el servicio Cliente al anular el pago ");
        }
    }

    public DatosRecepcionResponse obtenerUltimaTransaccion(String ip, String puertoDtf, String ipDtf) {
        String url = String.format("%s%s:%s%s%s%s", baseUrl, ip, puerto, ULTIMA_TRANSACCION_LAN, puertoDtf, ipDtf);

        try {
            ResponseEntity<DatosRecepcionResponse> response = restTemplate.getForEntity(url, DatosRecepcionResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                log.error("Respuesta no satisfactoria al obtenerUltima transaccion en cliente ip:{} en Datafast {}:{} statusCode:{}", ip, ipDtf, puertoDtf, response.getStatusCode());
                throw new HttpServerErrorException(response.getStatusCode(), "Error en el servicio Cliente respuesta no satisfactoria");
            }
        } catch (HttpServerErrorException e) {
            log.error("ERROR: al enviar la solicitud Anular Pago ip:{} en Datafast {}:{} statusCode ", ip, ipDtf, puertoDtf, e);
            throw new HttpServerErrorException(e.getStatusCode(), "Error en el servicio Cliente obtenerUltimaTransaccion ");
        }
    }

}

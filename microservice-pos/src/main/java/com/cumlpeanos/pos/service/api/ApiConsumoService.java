package com.cumlpeanos.pos.service.api;

import com.cumlpeanos.pos.models.api.DatosEnvioRequest;
import com.cumlpeanos.pos.models.api.DatosRecepcionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    //TODO http://host|ip:8080/endpoint ->
    private static final String BASE_URL="http://";
    private static final String PUERTO=":8080/";
    private static final String PROCESAR_PAGO="pos/procesarPago/";
    private static final String ANULAR_PAGO="anular-pago/";
    private static final String ULTIMA_TRANSACCION="ultima-transaccion/";
    private static final String LISTA_PUERTOS_COM="listaPuertosCom";
    private final RestTemplate restTemplate;

    public DatosRecepcionResponse procesarPago(String ip, String puertoCom, DatosEnvioRequest request){ //datos null !
        String url=BASE_URL+ip+PUERTO+PROCESAR_PAGO+puertoCom;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));

        HttpEntity<DatosEnvioRequest> entity = new HttpEntity<>(request,headers);

        try {
            ResponseEntity<DatosRecepcionResponse> response = restTemplate.postForEntity(url,entity, DatosRecepcionResponse.class);
            if (response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            } else {
                log.error("Respuesta no satisfactoria en el Cliente ip:{} en el puerto COM: {} statusCode:{}", ip, puertoCom, response.getStatusCode());
                throw new HttpServerErrorException(response.getStatusCode(), "Error en el servicio");
            }
        }catch (HttpServerErrorException e){
            log.error("ERROR: al enviar la solicitud al cliente ip:{} en el puerto COM: {} statusCode:{} ", ip, puertoCom, e.getStatusCode());
            throw new HttpServerErrorException(e.getStatusCode(), "Error en el servicio Cliente");
        }

    }

    public Map<String, String> listarPuertos(String ip) {
        String url = BASE_URL + ip + PUERTO + LISTA_PUERTOS_COM;

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

}

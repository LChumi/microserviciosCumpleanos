package com.cumlpeanos.pos.service.api;

import com.cumlpeanos.pos.models.api.DatosRecepcionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiConsumoService {

    //TODO http://host|ip:8080/endpoint ->
    private static final String BASE_URL="http://";
    private static final String PUERTO=":8080/";
    private static final String PROCESAR_PAGO="pos/procesarPago";
    private static final String ANULAR_PAGO="anular-pago/";
    private static final String ULTIMA_TRANSACCION="ultima-transaccion/";
    private final RestTemplate restTemplate;

    public DatosRecepcionResponse procesarPago(String ip, String puertoCom){ //datos null !
        String url=BASE_URL+ip+PUERTO+PROCESAR_PAGO+puertoCom;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));

        HttpEntity<DatosRecepcionResponse> entity = new HttpEntity<>(headers);

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

}

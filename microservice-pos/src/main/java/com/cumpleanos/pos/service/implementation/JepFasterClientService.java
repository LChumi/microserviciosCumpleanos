package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.common.exception.ApiResponse;
import com.cumpleanos.pos.persistence.api.jep.JepRequestQr;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import com.cumpleanos.pos.service.exception.HttpResponseHandler;
import com.cumpleanos.pos.service.http.IJepFasterClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JepFasterClientService {

    private final IJepFasterClient jepClient;

    public ApiResponse<JepResponseQr> getQR(JepRequestQr request){
        return HttpResponseHandler.handle(() ->
                jepClient.getQR(request),
                "Error en la obtencion de QR");
    }
}

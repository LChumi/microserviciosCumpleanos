package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.service.http.HttpResponseHandler;
import com.cumpleanos.assist.service.http.MongoClient;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.common.records.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
public class SessionServiceImpl {

    private final MongoClient mongoClient;

    public ServiceResponse saveSession(SessionDTO dto){
        return HttpResponseHandler.handle(() -> mongoClient.saveSession(dto),
                "Error al guardar la sesion");
    }
}

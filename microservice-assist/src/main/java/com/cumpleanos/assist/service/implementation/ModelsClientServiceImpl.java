package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.service.http.HttpResponseHandler;
import com.cumpleanos.assist.service.http.IModelsClient;
import com.cumpleanos.core.models.entities.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ModelsClientServiceImpl {

    private final IModelsClient modelsClient;

    public Usuario getUsuario(String usrId) {
        return HttpResponseHandler.handle(() -> modelsClient.findByUsrId(usrId),
                "Error al obtener el usuario con id: " + usrId);
    }
}

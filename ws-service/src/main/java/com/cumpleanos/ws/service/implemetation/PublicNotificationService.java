package com.cumpleanos.ws.service.implemetation;

import com.cumpleanos.ws.config.handler.NotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PublicNotificationService {

    private final NotificationHandler notificationHandler;

    public void bloquearContenedor(String id , String usuario){
        notificationHandler.broadcast("Usuario "+ usuario + "bloqueo el contenedor "+ id);
    }
}

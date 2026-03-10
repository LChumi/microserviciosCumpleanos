package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.auth.AuthenticationRequest;
import com.cumpleanos.assist.persistence.inmutables.UserResponse;
import com.cumpleanos.assist.service.exception.BadCredentialsException;
import com.cumpleanos.assist.utils.MailTemplateLoader;
import com.cumpleanos.common.records.EmailRecord;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.common.records.SessionDTO;
import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.core.models.entities.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioServiceImpl {

    private final ClientServiceImpl clienteService;
    private final SessionServiceImpl sessionService;

    public UserResponse login(AuthenticationRequest request, HttpServletRequest httpRequest) {
        Usuario user = clienteService.login(request.nombreUsuario(), request.clave());
        if (user == null) {
            throw new BadCredentialsException("Usuario no autenticado");
        }

        SessionDTO dto = new SessionDTO(
                null,
                UUID.randomUUID().toString(),
                user.getUsrId(),
                httpRequest.getRemoteAddr(),
                httpRequest.getHeader("User-Agent"),
                Instant.now(),
                Instant.now(),
                true,
                Instant.now().plus(Duration.ofHours(4))
        );

        ServiceResponse response = sessionService.saveSession(dto);
        if (response.success()) {
            log.info(response.message());
        } else {
            log.info(response.message());
        }

        return new UserResponse(user.getId(), user.getUsrId(), user.getNombre());
    }

    public ServiceResponse recoveryPassword(String usr_id) {
        Usuario usuario = clienteService.getUsuario(usr_id);
        if (usuario == null) {
            return new ServiceResponse("Usuario no encontrado.", false);
        }
        Empleado empleado = clienteService.getEmpledo(usuario.getId());
        if (empleado == null) {
            return new ServiceResponse("Empleado no tiene correo registrado.", false);
        }
        String asunto = "Recuperación de contraseña de acceso a la plataforma";

        Map<String, String> variables = new HashMap<>();
        variables.put("nombre", usuario.getNombre());
        variables.put("clave", usuario.getClave());

        String mensaje = MailTemplateLoader.loadAndFillTemplate("recoveryPassword.html", variables);

        try {
            EmailRecord email = new EmailRecord(
                    new String[]{empleado.getMailEmpresa()},
                    asunto,
                    mensaje
            );
            clienteService.enviarEmail(email);
            return new ServiceResponse("Su contraseña ha sido enviada al correo: " + empleado.getMailEmpresa(), true);
        } catch (Exception e) {
            return new ServiceResponse("Error al enviar el correo.", false);
        }
    }

}
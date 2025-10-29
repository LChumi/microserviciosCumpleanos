package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.auth.AuthenticationRequest;
import com.cumpleanos.assist.persistence.inmutables.UserResponse;
import com.cumpleanos.assist.utils.MailTemplateLoader;
import com.cumpleanos.common.records.EmailRecord;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.core.models.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioServiceImpl {

    private final ClientServiceImpl clienteService;

    public UserResponse login(AuthenticationRequest request) {
        Usuario usuario = clienteService.login(request.nombreUsuario(), request.clave());
        if (usuario == null) {
            return null;
        }
        return new UserResponse(usuario.getId(), usuario.getUsrId(), usuario.getNombre());
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

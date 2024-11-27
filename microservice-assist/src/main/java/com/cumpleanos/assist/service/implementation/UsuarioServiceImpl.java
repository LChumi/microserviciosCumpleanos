package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.auth.AuthenticationRequest;
import com.cumpleanos.assist.persistence.dto.ServiceResponse;
import com.cumpleanos.assist.persistence.dto.UserResponse;
import com.cumpleanos.core.models.dto.EmailRecord;
import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.core.models.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioServiceImpl {

    private final ClientServiceImpl clienteService;

    public UserResponse login(AuthenticationRequest request){
        Usuario usuario = clienteService.login(request.nombreUsuario(), request.clave());
        if (usuario == null) {
            return null;
        }
        return new UserResponse(usuario.getId(), usuario.getUsrId(), usuario.getNombre());
    }

    public ServiceResponse recoveryPassword(String usr_id){
        Usuario usuario = clienteService.getUsuario(usr_id);
        if (usuario == null) {
            return new ServiceResponse("Usuario no encontrado.");
        }
        Empleado empleado = clienteService.getEmpledo(usuario.getId());
        if (empleado == null) {
            return new ServiceResponse("Empleado no tiene correo registrado.");
        }
        String asunto = "Recuperación de contraseña de acceso a la plataforma";
        String mensaje = "Estimado/a " + usuario.getNombre() + ",\n\n" +
                "Hemos recibido una solicitud para recuperar tu contraseña.\n\n" +
                "Tu contraseña de acceso es la siguiente: \n\n" +
                "Contraseña: " + usuario.getClave() + "\n\n" +
                "Por razones de seguridad, te recomendamos cambiar tu contraseña tan pronto como accedas a la plataforma. Si no has solicitado esta recuperación, por favor contacta al soporte inmediatamente.\n\n" +
                "Atentamente,\nEl equipo de soporte";
        try {
            EmailRecord email = new EmailRecord(
              new String[]{empleado.getMailEmpresa()},
              asunto,
              mensaje
            );
            clienteService.enviarEmail(email);
            return new ServiceResponse("Correo con la contraseña enviado correctamente.");
        }catch (Exception e) {
            return new ServiceResponse("Error al enviar el correo, por favor intenta más tarde.");
        }
    }

}

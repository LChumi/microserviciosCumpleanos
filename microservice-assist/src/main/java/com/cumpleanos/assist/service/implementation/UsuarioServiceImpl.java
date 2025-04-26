package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.auth.AuthenticationRequest;
import com.cumpleanos.assist.persistence.inmutables.UserResponse;
import com.cumpleanos.common.records.EmailRecord;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.core.models.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String mensaje = """
                <div style='font-family: Arial, sans-serif; background-color: #354edc; padding: 20px;'>
                
                    <p>Estimado/a <strong>%s</strong>,</p>
                
                    <p>Hemos recibido una solicitud para <strong>recuperar tu contraseña</strong>.</p>
                
                    <div style="background-color: #f8f8f8; padding: 15px; border-left: 4px solid #007BFF; margin: 20px 0;">
                        <p style="margin: 0;">Tu contraseña de acceso es la siguiente:</p>
                        <p style="font-size: 18px; font-weight: bold; color: #333;">Contraseña: %s</p>
                    </div>
                
                    <p>Por razones de seguridad, te recomendamos <strong>cambiar tu contraseña</strong> tan pronto como accedas a la plataforma.</p>
                
                    <p>Si no has solicitado esta recuperación, por favor contacta al soporte inmediatamente.</p>
                
                    <p>Atentamente,<br><strong>El equipo de soporte</strong></p>
                </div>
                """.formatted(usuario.getNombre(), usuario.getClave());

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

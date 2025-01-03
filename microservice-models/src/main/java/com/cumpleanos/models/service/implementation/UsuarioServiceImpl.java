package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Usuario;
import com.cumpleanos.models.persistence.repository.UsuarioRepository;
import com.cumpleanos.models.service.exception.BadCredentialsException;
import com.cumpleanos.models.service.interfaces.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements IUsuarioService {

    private final UsuarioRepository repository;

    @Override
    public CrudRepository<Usuario, Long> getRepository() {
        return repository;
    }

    @Override
    public Usuario findByUsername(String username) {
        return repository.findByUsrId(username.toUpperCase()).orElse(null);
    }

    @Override
    public Usuario findByUsrIdAnClave(String usrId, String clave) {
        return repository.findByUsrIdAndClave(usrId.toUpperCase(), clave).orElseThrow(() -> new BadCredentialsException("Nombre de usuario o clave incorrectos"));
    }
}

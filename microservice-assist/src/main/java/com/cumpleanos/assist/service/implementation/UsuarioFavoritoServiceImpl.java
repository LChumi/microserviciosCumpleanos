package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.entity.UsuarioFavoritos;
import com.cumpleanos.assist.persistence.repository.ProgramaWRepository;
import com.cumpleanos.assist.persistence.repository.UsuarioFavoritoRepository;
import com.cumpleanos.assist.service.interfaces.IUsuarioFavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioFavoritoServiceImpl extends GenericServiceImpl<UsuarioFavoritos, Long> implements IUsuarioFavoritoService {

    private final UsuarioFavoritoRepository repository;
    private final ProgramaWRepository programaRepository;

    @Override
    public CrudRepository<UsuarioFavoritos, Long> getRepository() {
        return repository;
    }

    @Override
    public Set<UsuarioFavoritos> getFavoritosByUser(Long idUsuario) {
        return repository.findByUsuario_Id(idUsuario);
    }

    @Override
    public UsuarioFavoritos saveFavorito(Long idUsuario, Long empresa, String path) {
        return null;
    }


}

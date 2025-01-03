package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.entity.UsuarioFavoritos;
import com.cumpleanos.assist.persistence.inmutables.FavoriteRequest;
import com.cumpleanos.assist.persistence.repository.ProgramaWRepository;
import com.cumpleanos.assist.persistence.repository.UsuarioFavoritoRepository;
import com.cumpleanos.assist.service.exception.FavoriteNotFoundException;
import com.cumpleanos.assist.service.exception.ProgramaNotFoundException;
import com.cumpleanos.assist.service.exception.UserNotFoundException;
import com.cumpleanos.assist.service.interfaces.IUsuarioFavoritoService;
import com.cumpleanos.core.models.entities.ProgramaW;
import com.cumpleanos.core.models.entities.Usuario;
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
    private final ClientServiceImpl modelsClient;

    @Override
    public CrudRepository<UsuarioFavoritos, Long> getRepository() {
        return repository;
    }

    @Override
    public Set<UsuarioFavoritos> getFavoritosByUserAndEmpresa(Long idUsuario, Long empresa) {
        return repository.findByUsuario_IdAndEmpresa(idUsuario, empresa);
    }

    @Override
    public UsuarioFavoritos saveFavorito(FavoriteRequest request) {

        Usuario usuario = modelsClient.getUsuarioByCodigo(request.idUsuario());
        if (usuario == null) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
        ProgramaW programa = programaRepository.findByPath(request.path())
                .orElseThrow(() -> new ProgramaNotFoundException("Programa no encontrado"));
        return super.save(UsuarioFavoritos.builder()
                .usuario(usuario)
                .programa(programa)
                .empresa(request.empresa())
                .build());
    }

    @Override
    public UsuarioFavoritos getFavoritoByUsuarioEmpresaPath(FavoriteRequest request) {
        ProgramaW programa = programaRepository.findByPath(request.path())
                .orElseThrow(() -> new ProgramaNotFoundException("Programa no encontrado"));
        return repository.findByUsuario_IdAndEmpresaAndPrograma(request.idUsuario(), request.empresa(), programa).orElseThrow(() -> new FavoriteNotFoundException("Favorito no encontrado"));
    }

}

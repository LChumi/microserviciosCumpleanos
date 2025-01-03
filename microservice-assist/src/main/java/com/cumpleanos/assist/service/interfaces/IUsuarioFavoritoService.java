package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.entity.UsuarioFavoritos;
import com.cumpleanos.assist.persistence.inmutables.FavoriteRequest;

import java.util.Set;

public interface IUsuarioFavoritoService extends IGenericService<UsuarioFavoritos, Long>{
    Set<UsuarioFavoritos> getFavoritosByUser(Long idUsuario);
    UsuarioFavoritos saveFavorito(FavoriteRequest request);
}

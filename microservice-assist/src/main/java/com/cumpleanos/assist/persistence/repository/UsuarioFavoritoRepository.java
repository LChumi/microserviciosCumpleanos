package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.assist.persistence.entity.UsuarioFavoritos;
import com.cumpleanos.core.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UsuarioFavoritoRepository extends JpaRepository<UsuarioFavoritos, Long> {
    Set<UsuarioFavoritos> findByUsuario_Id(Long id);
}

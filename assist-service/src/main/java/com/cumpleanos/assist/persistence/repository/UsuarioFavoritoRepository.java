package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.assist.persistence.entity.UsuarioFavoritos;
import com.cumpleanos.core.models.entities.ProgramaW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioFavoritoRepository extends JpaRepository<UsuarioFavoritos, Long> {

    Set<UsuarioFavoritos> findByUsuario_IdAndEmpresa(Long id, Long empresa);

    Optional<UsuarioFavoritos> findByUsuario_IdAndEmpresaAndPrograma(Long usuario, Long empresa, ProgramaW programa);
}

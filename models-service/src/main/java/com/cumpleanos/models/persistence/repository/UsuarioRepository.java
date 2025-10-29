package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsrId(String usrId);
    Optional<Usuario> findByUsrIdAndClave(String usrId, String clave);

}

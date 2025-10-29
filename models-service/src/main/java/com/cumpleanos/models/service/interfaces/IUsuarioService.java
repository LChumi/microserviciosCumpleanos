package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Usuario;

public interface IUsuarioService  extends GenericService<Usuario,Long>{

    Usuario findByUsername(String username);
    Usuario findByUsrIdAnClave(String usrId, String clave);
}

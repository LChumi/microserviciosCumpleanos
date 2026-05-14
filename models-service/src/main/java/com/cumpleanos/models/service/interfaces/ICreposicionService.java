package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.ids.CreposicionId;

import java.util.List;

public interface ICreposicionService extends GenericService<Creposicion, CreposicionId> {

    Boolean existCreposicion(Long empresa, Long codigo);

    Boolean existCreposicionByEmpresaAndReferencia(String referencia, Long empresa);

    ServiceResponse finalizarPedido(Long empresa, Long codigo, Long usrLiquida,Integer estado);

    void updateEstadoFallidoCreposicion(CreposicionId id);

    List<Creposicion> getByUsuario(Integer tipo, String usuario, Boolean finalizado);
}

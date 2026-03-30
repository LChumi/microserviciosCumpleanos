package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.ImporItemDTO;
import com.cumpleanos.core.models.entities.Imporitem;
import com.cumpleanos.core.models.ids.ImporitemId;

import java.math.BigInteger;
import java.util.List;

public interface IImporitemService extends GenericService<Imporitem, ImporitemId> {

    List<ImporItemDTO> getImporItemByCco(BigInteger cco, Long codProducto);

}

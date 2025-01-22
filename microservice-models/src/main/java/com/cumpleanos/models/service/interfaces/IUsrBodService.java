package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.UsrBod;
import com.cumpleanos.core.models.ids.UsrbodId;

import java.util.Set;

public interface IUsrBodService extends GenericService<UsrBod, UsrbodId>{
    Set<UsrBod> listBodByUser(Long usuario ,Long empresa );
}

package com.cumpleanos.mongo.service.interfaces;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.common.records.SessionDTO;
import com.cumpleanos.mongo.persistence.models.Session;

public interface ISessionService extends IGenericService<Session, String> {

    ServiceResponse saveSession(SessionDTO session);

    ServiceResponse getLastLogin(String userId);

}
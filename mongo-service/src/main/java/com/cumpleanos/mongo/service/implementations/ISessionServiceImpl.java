package com.cumpleanos.mongo.service.implementations;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.common.records.SessionDTO;
import com.cumpleanos.mongo.persistence.models.Session;
import com.cumpleanos.mongo.persistence.repository.SessionRepository;
import com.cumpleanos.mongo.service.interfaces.ISessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ =  {@Autowired})
public class ISessionServiceImpl extends GenericServiceImpl<Session, String> implements ISessionService {

    private final SessionRepository repository;

    @Override
    public CrudRepository<Session, String> getRepository() {
        return repository;
    }

    @Override
    public ServiceResponse saveSession(SessionDTO dto) {
        try {
            //Convertir DTO a entidad
            Session session = new Session();
            session.setId(dto.id());
            session.setSessionId(dto.sessionId());
            session.setUserId(dto.userId());
            session.setIpAddress(dto.ipAddress());
            session.setUserAgent(dto.userAgent());
            session.setLoginTime(dto.loginTime());
            session.setLastActivityTime(dto.lastActivityTime());
            session.setActive(dto.active());
            session.setExpireAt(dto.expireAt());

            repository.save(session);

            return new ServiceResponse("Session saved", true);
        } catch (Exception e) {
            return new ServiceResponse("Error al guardar la sesion", false);
        }
    }


}
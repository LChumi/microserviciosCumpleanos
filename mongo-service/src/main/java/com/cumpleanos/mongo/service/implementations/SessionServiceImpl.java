package com.cumpleanos.mongo.service.implementations;

import com.cumpleanos.mongo.persistence.models.Session;
import com.cumpleanos.mongo.persistence.repository.SessionRepository;
import com.cumpleanos.mongo.service.interfaces.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ =  {@Autowired})
public class SessionServiceImpl extends GenericServiceImpl<Session, String> implements SessionService {

    private final SessionRepository repository;

    @Override
    public CrudRepository<Session, String> getRepository() {
        return repository;
    }
}

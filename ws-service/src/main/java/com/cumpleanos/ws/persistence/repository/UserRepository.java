package com.cumpleanos.ws.persistence.repository;

import com.cumpleanos.ws.persistence.models.User;
import com.cumpleanos.ws.utils.Status;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Flux<User> findAllByStatus(Status status);
}


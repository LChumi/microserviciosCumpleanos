package com.cumpleanos.ws.service.implemetation;

import com.cumpleanos.ws.persistence.models.User;
import com.cumpleanos.ws.persistence.repository.UserRepository;
import com.cumpleanos.ws.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<User> login(String nickname){
        return userRepository.findById(nickname)
                .switchIfEmpty(Mono.defer(() -> {
                    User u = new User();
                    u.setNickName(nickname);
                    u.setStatus(Status.ONLINE);
                    return userRepository.save(u);
                }))
                .flatMap(u -> {
                    u.setStatus(Status.ONLINE);
                    return userRepository.save(u);
                });
    }

    public Mono<User> logout(String nickname){
        return userRepository.findById(nickname)
                .flatMap(user -> {
                    user.setStatus(Status.OFFLINE);
                    return userRepository.save(user);
                });
    }

    public Flux<User> getOnlineUsers(){
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
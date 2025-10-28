package com.cumpleanos.ws.presentation.controller;

import com.cumpleanos.ws.persistence.models.User;
import com.cumpleanos.ws.service.implemetation.UserService;
import com.cumpleanos.ws.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ws")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    @PostMapping("/users/register")
    public Mono<User> register(@RequestBody User user){
        user.setStatus(Status.OFFLINE);
        return userService.save(user);
    }

    @PostMapping("/users/logout/{nick}")
    public Mono<User> logout(@PathVariable String nick){
        return userService.logout(nick);
    }

    @GetMapping("/users/online")
    public Flux<User> getOnlineUsers(){
        return userService.getOnlineUsers();
    }
}

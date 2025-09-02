package com.eventapp.auth_service.controller;

import com.eventapp.auth_service.model.User;
import com.eventapp.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register-participant")
    public String registerParticipant(@RequestBody User user){
        userService.registerUser(user, "PARTICIPANT");
        return "Participante registrado con existo";
    }

    @PostMapping("/register-organizer")
    public String registerOrganizer(@RequestBody User user){
        userService.registerUser(user, "ORGANIZER");
        return "Organizardor registrado con existo";
    }

}

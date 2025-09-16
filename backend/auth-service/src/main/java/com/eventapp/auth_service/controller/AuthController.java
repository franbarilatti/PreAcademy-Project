package com.eventapp.auth_service.controller;

import com.eventapp.auth_service.dto.LoginRequest;
import com.eventapp.auth_service.dto.LoginResponse;
import com.eventapp.auth_service.model.User;
import com.eventapp.auth_service.service.AuthService;
import com.eventapp.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

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

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader){

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.badRequest().body("Autorizacion no encontrada o invalida");
        }

        String token = authHeader.substring(7);

        authService.logout(token);

        return ResponseEntity.ok("Sesion cerrada");

    }


}

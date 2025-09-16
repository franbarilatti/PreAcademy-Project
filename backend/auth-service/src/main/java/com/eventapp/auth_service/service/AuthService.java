package com.eventapp.auth_service.service;

import com.eventapp.auth_service.dto.LoginRequest;
import com.eventapp.auth_service.dto.LoginResponse;
import com.eventapp.auth_service.model.InvalidateToken;
import com.eventapp.auth_service.model.User;
import com.eventapp.auth_service.repository.InvalidatedTokenRepository;
import com.eventapp.auth_service.repository.UserRepository;
import com.eventapp.auth_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final InvalidatedTokenRepository invalidatedTokenRepository;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, InvalidatedTokenRepository invalidatedTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.invalidatedTokenRepository = invalidatedTokenRepository;
    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Usuario y/o contraseña incorrecto/s");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token);
    }

    public void logout(String token){
        InvalidateToken invalidateToken = new InvalidateToken();
        invalidateToken.setToken(token);
        invalidateToken.setInvalidatedAt(LocalDateTime.now());
        invalidatedTokenRepository.save(invalidateToken);
    }

    public boolean isTokenBlacklisted(String token){
        return invalidatedTokenRepository.findByToken(token).isPresent();
    }


}

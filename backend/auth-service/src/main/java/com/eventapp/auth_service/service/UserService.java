package com.eventapp.auth_service.service;

import com.eventapp.auth_service.model.Role;
import com.eventapp.auth_service.model.User;
import com.eventapp.auth_service.repository.RoleRepository;
import com.eventapp.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user, String roleName){
        if (userRepository.existByUsername(user.getUsername())){
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (userRepository.existByEmail(user.getEmail())){
            throw new RuntimeException("El email ya existe");
        }

        Role role = roleRepository.findByName(roleName).
                orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}

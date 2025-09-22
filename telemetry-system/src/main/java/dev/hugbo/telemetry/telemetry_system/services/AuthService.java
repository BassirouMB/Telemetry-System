package dev.hugbo.telemetry.telemetry_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.hugbo.telemetry.telemetry_system.entities.User;
import dev.hugbo.telemetry.telemetry_system.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Boolean authenticate(String name, String password) {
        User existingUser = userRepository.findByName(name);
        return existingUser != null && encoder.matches(password, existingUser.getPassword());
    }
}


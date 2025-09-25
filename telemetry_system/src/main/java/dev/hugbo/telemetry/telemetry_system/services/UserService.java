package dev.hugbo.telemetry.telemetry_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import dev.hugbo.telemetry.telemetry_system.entities.User;
import dev.hugbo.telemetry.telemetry_system.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;


    public User createUser(String name, String password, Boolean isAdmin) {
        User user = new User();
        user.setName(name);
        user.setPassword(encoder.encode(password));
        user.setIsAdmin(isAdmin);
        return userRepository.save(user);
        
    }
}

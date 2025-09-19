package dev.hugbo.telemetry.telemetry_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hugbo.telemetry.telemetry_system.entities.User;
import dev.hugbo.telemetry.telemetry_system.repositories.UserRepository;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder encoder;

    public AuthController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password) {
        User existingUser = userRepository.findByName(name);
        if (existingUser != null && encoder.matches(password, existingUser.getPassword())) {
            return "Login successful";
        }
        return "Login failed";
    }


}

    
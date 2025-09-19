package dev.hugbo.telemetry.telemetry_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hugbo.telemetry.telemetry_system.entities.User;
import dev.hugbo.telemetry.telemetry_system.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserController() {
        System.out.println("UserController initialized!");
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public User createUser(@RequestParam String name, @RequestParam String password, @RequestParam String role) {
        
        User user = new User();
        user.setName(name);
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        return userRepository.save(user);
    }
}

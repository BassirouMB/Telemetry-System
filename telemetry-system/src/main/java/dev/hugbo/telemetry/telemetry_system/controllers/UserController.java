package dev.hugbo.telemetry.telemetry_system.controllers;

import dev.hugbo.telemetry.telemetry_system.entities.User;
import dev.hugbo.telemetry.telemetry_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController() {
        System.out.println("UserController initialized!");
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user, @RequestParam String name, @RequestParam String password, @RequestParam Boolean isAdmin) {
        user.setName(name);
        user.setPassword(password);
        user.setIsAdmin(isAdmin);
        return userRepository.save(user);
    }
}

package dev.hugbo.telemetry.telemetry_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hugbo.telemetry.telemetry_system.entities.User;

import dev.hugbo.telemetry.telemetry_system.repositories.UserRepository;
import dev.hugbo.telemetry.telemetry_system.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public User createUser(@RequestParam String name, @RequestParam String password, @RequestParam Boolean isAdmin) {
        return userService.createUser(name, password, isAdmin);
    }
}

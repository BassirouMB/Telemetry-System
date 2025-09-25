package dev.hugbo.telemetry.telemetry_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.hugbo.telemetry.telemetry_system.entities.User;
import dev.hugbo.telemetry.telemetry_system.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;


    public User createUser(String name, String password, Boolean isAdmin) {
        
        if (userRepository.findByName(name) != null) {
            throw new IllegalArgumentException("User with name " + name + " already exists.");
        } else {        
            User user = new User();
            user.setName(name);
            user.setPassword(encoder.encode(password));
            user.setIsAdmin(isAdmin);
            return userRepository.save(user);
        }
    }

    public User deleteUser(String name) {
        User user = userRepository.findByName(name);
        if (user != null) {
            userRepository.delete(user);
            return user;
        } else {
            throw new IllegalArgumentException("User with name " + name + " does not exist");
        }
    }
}

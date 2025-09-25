package dev.hugbo.telemetry.telemetry_system.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String name;
    
    private String password;
    private Boolean isAdmin;


    // Getters
    public String getName() { return name; }
    public String getPassword() { return password; }
    public Boolean getIsAdmin() { return isAdmin; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
    public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }
}


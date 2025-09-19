package dev.hugbo.telemetry.telemetry_system.repositories;

import dev.hugbo.telemetry.telemetry_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}


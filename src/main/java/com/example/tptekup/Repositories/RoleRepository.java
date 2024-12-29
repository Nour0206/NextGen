package com.example.tptekup.Repositories;

import com.example.tptekup.Entities.Enum.ERole;
import com.example.tptekup.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName( ERole name);
}

package com.example.tptekup.Repositories;


import com.example.tptekup.Entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken (String token);
}

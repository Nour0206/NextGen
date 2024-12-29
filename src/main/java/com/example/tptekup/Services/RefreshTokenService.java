package com.example.tptekup.Services;

import com.example.tptekup.Entities.RefreshToken;
import com.example.tptekup.Repositories.RefreshTokenRepository;
import com.example.tptekup.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${nextgen.app.jwtrefresh.expirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

     public RefreshToken createRefreshToken (Long userId) {
         RefreshToken refreshToken = new RefreshToken();

         refreshToken.setUser(userRepository.findById(userId).get());
         refreshToken.setExpiryDate(Instant.now().
                 plusMillis(refreshTokenDurationMs));
         refreshToken.setToken(UUID.randomUUID().toString());

         refreshToken = refreshTokenRepository.save(refreshToken);
         return refreshToken;
     }
}

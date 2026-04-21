package dgsw.service;

import dgsw.domain.RefreshToken;
import dgsw.domain.User;
import dgsw.security.config.jwt.JwtProperties;
import dgsw.security.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements RefreshTokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final JwtProperties jwtProperties;

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalAccessException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        Duration accessExpiration = Duration
    }
}

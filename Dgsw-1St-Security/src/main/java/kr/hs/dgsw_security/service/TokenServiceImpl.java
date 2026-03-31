package kr.hs.dgsw_security.service;

import kr.hs.dgsw_security.config.jwt.JwtProperties;
import kr.hs.dgsw_security.config.jwt.TokenProvider;
import kr.hs.dgsw_security.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final JwtProperties jwtProperties;

    @Override
    public String createNewAccessToken(String refreshToken) {
        if(tokenProvider.validToken(refreshToken) == false) {
            throw new IllegalStateException("invalid refresh token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);
        Duration duration = Duration.ofMinutes(
               Long.parseLong(jwtProperties.getAccessExpirationMinutes())
        );
        return tokenProvider.generateToken(user, duration);
    }
}

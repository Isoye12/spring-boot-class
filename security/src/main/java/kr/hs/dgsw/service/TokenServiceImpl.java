package kr.hs.dgsw.service;

import kr.hs.dgsw.config.jwt.JwtProperties;
import kr.hs.dgsw.config.jwt.TokenProvider;
import kr.hs.dgsw.domain.User;
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
    public String createNewAccessToken(String refreshToken) throws Exception {

//        refreshTokenService.findByRefreshToken().getUserId()

//        userService.findById();

//        Duration.ofMinutes();

        return null;
    }

}

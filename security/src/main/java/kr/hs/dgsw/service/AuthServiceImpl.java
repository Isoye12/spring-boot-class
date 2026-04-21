package kr.hs.dgsw.service;

import kr.hs.dgsw.config.jwt.JwtProperties;
import kr.hs.dgsw.config.jwt.TokenProvider;
import kr.hs.dgsw.domain.User;
import kr.hs.dgsw.dto.AuthRequest;
import kr.hs.dgsw.dto.AuthResponse;
import kr.hs.dgsw.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final JwtProperties jwtProperties;

    @Override
    public AuthResponse login(AuthRequest request) {

//        authenticationManager.authenticate();

//        Duration.ofMinutes();

//        Duration.ofDays();

//        refreshTokenService.saveOrUpdate();
        Authentication token = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return null;
    }
}

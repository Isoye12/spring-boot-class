package kr.hs.dgsw_security.service;

import kr.hs.dgsw_security.domain.RefreshToken;
import kr.hs.dgsw_security.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository
                .findByRefreshToken(refreshToken)
                .orElseThrow(
                        () -> new IllegalStateException("Unexcepted token")
                );
    }
}

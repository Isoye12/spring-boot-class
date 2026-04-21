package kr.hs.dgsw.service;

import kr.hs.dgsw.domain.RefreshToken;

public interface RefreshTokenService {
    RefreshToken findByRefreshToken(String refreshToken);

    RefreshToken saveOrUpdate(
            Long userId, String refreshToken
    );
}

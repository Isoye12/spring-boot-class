package kr.hs.dgsw_security.service;

import kr.hs.dgsw_security.domain.RefreshToken;

public interface RefreshTokenService {
    RefreshToken findByRefreshToken(String refreshToken);
    RefreshToken saveOrUpdate(Long userId, String refreshToken);
}

package dgsw.service;

import dgsw.domain.RefreshToken;

public interface TokenService {
    String createNewAccessToken(String refreshToken);
}

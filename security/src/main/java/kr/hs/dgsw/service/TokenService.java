package kr.hs.dgsw.service;

public interface TokenService {
    String createNewAccessToken(String refreshToken) throws Exception;
}

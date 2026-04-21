package kr.hs.dgsw.service;

import kr.hs.dgsw.dto.AuthRequest;
import kr.hs.dgsw.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}

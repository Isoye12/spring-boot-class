package kr.hs.dgsw_security.service;

import kr.hs.dgsw_security.dto.AuthRequest;
import kr.hs.dgsw_security.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest _req);
}

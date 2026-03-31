package kr.hs.dgsw_security.service;

import kr.hs.dgsw_security.domain.User;
import kr.hs.dgsw_security.dto.SignupRequest;

public interface UserService {
    User signUp(SignupRequest req);
    User findById(Long id);
}

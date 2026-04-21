package kr.hs.dgsw.service;

import kr.hs.dgsw.domain.User;
import kr.hs.dgsw.dto.SignUpRequest;

public interface UserService {
    User signUp(SignUpRequest request);

    User findById(Long id);
}

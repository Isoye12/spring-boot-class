package dgsw.service;

import dgsw.domain.User;
import dgsw.dto.SignUpRequest;

public interface UserService {
    User signUp(SignUpRequest request);
    User findById(Long id);
}

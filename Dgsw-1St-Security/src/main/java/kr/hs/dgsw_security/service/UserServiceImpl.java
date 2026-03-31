package kr.hs.dgsw_security.service;

import kr.hs.dgsw_security.domain.User;
import kr.hs.dgsw_security.domain.UserRole;
import kr.hs.dgsw_security.dto.SignupRequest;
import kr.hs.dgsw_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUp(SignupRequest req) {
        return userRepository
                .save(
                    User.builder() /* 이게 빌더 패턴인거임. */
                           .email(req.getEmail())
                           .password(passwordEncoder.encode(req.getPassword()))
                            .role(UserRole.USER)
                            .build()
                );
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Unexcepted token")
        );
    }
}
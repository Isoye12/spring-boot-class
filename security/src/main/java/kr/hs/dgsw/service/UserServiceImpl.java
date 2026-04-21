package kr.hs.dgsw.service;

import kr.hs.dgsw.domain.User;
import kr.hs.dgsw.domain.UserRole;
import kr.hs.dgsw.dto.SignUpRequest;
import kr.hs.dgsw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUp(SignUpRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () ->
                                new IllegalArgumentException("Unexpected token")
                );
    }

}

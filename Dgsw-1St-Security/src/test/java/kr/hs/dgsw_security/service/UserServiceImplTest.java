package kr.hs.dgsw_security.service;

import kr.hs.dgsw_security.domain.User;
import kr.hs.dgsw_security.dto.SignupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {
    @Autowired /** 테스트니까 이거 걍 써 */
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    void signUp() {
        // given
        final String rawPassword = "password1";
        SignupRequest req = new SignupRequest();
        req.setEmail("test@test.com");
        req.setPassword(rawPassword);

        // when
        User savedUser = userService.signUp(req);

        // then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isNotNull();
        assertThat(savedUser.getPassword()).isNotEqualTo(rawPassword);
        assertThat(passwordEncoder.matches(rawPassword, savedUser.getPassword())).isTrue();

    }
}
package kr.hs.dgsw_security.security;

import kr.hs.dgsw_security.domain.User;
import kr.hs.dgsw_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * 사용자가 이메일/비밀번호 : json형식으로 서버에 전송
 * Security가 이메일을 뽑아서 loadUserByUsername()를 호출
 * 개발자는 Database에서 이메일로 회원정보 찾음 --> CustomUserDetails에 담아서 리턴
 * Security는 내부적으로 사용자가 입력한 비번 + UserDetails에 있는 암호화된 비번을 비교해서 승인여부결정.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    /* final이기 때문에 RequiredArgsConstructor로 생성자를 하나 생서왜줬음 */
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(username));
        return new CustomUserDetails(user);
    }
}
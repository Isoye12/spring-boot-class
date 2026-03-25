package kr.hs.dgsw_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * 처음에 우리가 서비스 실행을 딱 하면 서버를 올리면, 이 클래스가 바로 호출이 되어야 사용자 접근을 어떻게 제한시킴.
 * 그래서 Bean을 써주는 거임.
 * */
/*
 * HttpSecurity : 보안설정 만드는 놈.
 * SecurityFilterChain : 만들어진 보안.
 * */
@Configuration  /* 이 틀래스는 설정 클래스이다 라고 tomcat container에게 알려주는 것 */
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) /** csrf 공격 막을거면 이 부분을 지우는거임 */
            .sessionManagement(
                sm -> sm.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS /** session을 안쓴다는 뜻 */
                )
            )
            /* request의 url이 /api/auth/**이면 다 허용, 나머지는 안됨 */
            .authorizeHttpRequests(
                auth -> auth
                    .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
            );
        return http.build();
    }

    /* 유저가 입력한 비밀버호 암호화된 것만 디비에 저장되지 때문에 이 부분이 꼭 있어야함. */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    /*
     * AuthenticationManager : 총괄 매니저.
     * AuthenticationConfiguration : 설정. 시큐리티랑 관련된 것들을 설정하는것만 함.
     * */
}

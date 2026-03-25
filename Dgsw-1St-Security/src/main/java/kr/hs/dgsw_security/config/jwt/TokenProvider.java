package kr.hs.dgsw_security.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import kr.hs.dgsw_security.domain.User;
import kr.hs.dgsw_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenProvider {
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private SecretKey secretKey;

    /*
     * 생성자 전에 실행되는 함수
     * 메서드 안에서 객체를 만드는 방법 -> Bean
     * */
    @PostConstruct
    void inIt() {
        byte[] keyBytes = Decoders.BASE64.decode( jwtProperties.getSecretKey() );
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /*
     * Spring Bean lifesycle
     * 1. 스프링 컨테이너가 생성
     * 2. Bean 생성
     * 3. 의존성 주입
     * 4. @PostConstruct : 1번만 실행,  의존성 주입이 끝난 후에 초기화 작업을 수행하는 메서드에 붙이는 어노테이션
     * 5. Bean 사용
     * 6. 객체 소멸 Callback( @PreDestroy )
     * 7. 스프링 종료
     * */

    public String createToken(User user, Duration expireAt) {
        Date now = new Date(); /* 자바에서는 원래 calendar써야함 */
        return makeToken(new Date(now.getTime() + expireAt.toMillis()), user);
    }

    private String makeToken(Date expire, User user) {
        Date now = new Date();
        /*
         * 여기서 builder 패턴이 좋대여
         * 보통은 bouild() 메서드를 쓰는데 Jwts는 compact()를 사용
         * */
        return Jwts.builder()
                .header().type("JWT")
                .and()                             /* header와 claim의 구분자라고 생각하면됨. 다른 기능은 X */
                .issuer(jwtProperties.getIssuer()) /* token 발급자 */
                .issuedAt(now)                     /* token 발급 시간 */
                .expiration(expire)                /* token 만료 시간 */
                .subject(user.getEmail())          /* token 주체 */
                .claim("id", user.getId())         /* claim 추가, 원하는 값을 (key, value) 형식으로 추가가능 */
                .signWith(secretKey)               /* 서명 --> header + claim + secretKey */
                .compact();
    }
    /*
     * claim
     * 비공개 - 내만 쓰는거 중복신경 안써도됨
     * 공개 - 내가 LIB를 공개, 중복허용 안됨
     * */

    /* token 유효성 검증 테스트 */
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (Exception e){ /* 원래는 아래 예외 목록들 포함하여 다 처리해줘야함. */
            return false;
        }
    }
    /*
     * 예외.
     * 서명이 조작 - SignatureException
     * 유효기간이 지난거 - ExpiredJwtException
     * 토큰 모양 이상한거 - MalformedJwtException
     * 토큰값 없을때 - IllegalArgumentException
     * 지원하지 않는 양식 - UnsupportedJwtException
     * */

    /* claim 정보(사용자 정보)를 가져오기 - Payload */
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /* subject 뽑아내기, 등록된 claim 정보 */
    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    /* 비공개(공개도 가능) claim은 어떻게 뽑아내죠? Map 형태로 정보를 넣어줘야함 */
    public Long getUserId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    /* Security랑 연결하기
     * 역할 - JWT애서 정보를 뽑아서 Authentication의 출입증으로 작용
     * Security가 알아먹을 수 있는 객체로 변환해주는 역할
     * 아는거
     *     - 사용자 정보: UserDetails
     *     - 인증: Authentication
     * */

    public Authentication getAuthentication(String token) {
        String email = getSubject(token); /* 시용자 이메일 */

    }
}
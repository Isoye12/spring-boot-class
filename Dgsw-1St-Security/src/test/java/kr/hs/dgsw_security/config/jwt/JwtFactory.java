package kr.hs.dgsw_security.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.*;

public class JwtFactory {
    private String subject = "test@dgsw.hs.kr";
    private Date issuedAt = new Date();
    private Date expiresAt = new Date(
            /* 나중에 만료 테스트 하고 싶으면 ofDays를 밀리초나 초로 넣어서 만료 테스트 해보기 */
            new Date().getTime() + Duration.ofDays(14).toMillis()
    );
    private Map<String, Object> claims = Collections.emptyMap(); /* 대문자로 시작하는 클래스 .메서드명 형태니까 static인거임 */

    @Builder
    public JwtFactory(String subject, Date issuedAt, Date expiresAt, Map<String, Object> claims) {
        this.subject = subject != null ? subject : this.subject; /* 사용자가 값을 만약 넣어줬으면 그걸로 아고 아니면 위에서 정의한거로 하기 */
        this.issuedAt = issuedAt != null ? issuedAt : this.issuedAt;
        this.expiresAt = expiresAt != null ? expiresAt : this.expiresAt;
        this.claims = claims != null ? claims : this.claims;
    }

    /* 기본값으로 토큰 생성해서 사용할때 */
    public static JwtFactory withDefaultValues() {
        return JwtFactory.builder().build();
    }

    public String createToken(JwtProperties jwtProperties) {
        SecretKey key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode( jwtProperties.getSecretKey() )
        );
        return Jwts.builder()
                .header().type("JWT")
                .and() /* and header */
                .issuer(jwtProperties.getIssuer())
                .issuedAt(issuedAt)
                .expiration(expiresAt)
                .subject(subject)
                .claims().add(claims)
                .and() /* and payload */
                .signWith(key)
                .compact();
    }
}

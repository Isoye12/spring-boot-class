package dgsw.security.config.jwt;

import dgsw.domain.User;
import dgsw.domain.UserRole;
import dgsw.repository.UserRepository;
import dgsw.security.config.config.JwtFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import javax.print.attribute.UnmodifiableSetException;
import java.time.Duration;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generatedBase64SecretKey(): HS256 비밀티를 생성할 수 있다")
    @Test
    void generatedBase64SecretKey() {
        SecretKey key = Jwts.SIG.HS256.key().build();
        byte[] encoded = key.getEncoded();
        System.out.println(Encoders.BASE64.encode(encoded));
    }

    @DisplayName("")
    @Test
    void generateToken() {
        // given
        User testUser = userRepository.save(
                User.builder()
                        .email("test@gmail.com")
                        .password("password")
                        .role(UserRole.USER)
                        .build()
        );

        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(jwtProperties.getRefreshExpirationDays()));

        // then
        Long userId = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey())))
                .build()
                .parseSignedClaims(token)
                .getPayload().get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
    }

    @DisplayName("")
    @Test
    void vaildToken_invaildToken() {
        // given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        // when
        boolean result = tokenProvider.validToken(token);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("vaildToken(): 유효한 토큰인 경우에 유효성 검증에 성공한다")
    @Test
    void vaildToken_vaildToken() {
        // given
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);

        // when
        boolean result = tokenProvider.validToken(token);

        // then
        assertThat(result).isTrue();
    }
}
package kr.hs.dgsw.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import kr.hs.dgsw.domain.RefreshToken;
import kr.hs.dgsw.domain.User;
import kr.hs.dgsw.repository.UserRepository;
import kr.hs.dgsw.security.CustomUserDetails;
import kr.hs.dgsw.service.RefreshTokenService;
import kr.hs.dgsw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private SecretKey secretKey;

    @PostConstruct
    void init() {
        byte[] bytes = Decoders.BASE64.decode(
                jwtProperties.getSecretKey()
        );
        this.secretKey = Keys.hmacShaKeyFor(bytes);
    }

    public Authentication getAuthentication(String token){
        String email = getSubject(token);
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("user not found with email : " + email)
        );
        CustomUserDetails userDetails = new CustomUserDetails(user);
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }


    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(
                new Date(now.getTime() + expiredAt.toMillis()),
                user
        );
    }

    private String makeToken(Date expiry, User user) {
        Date now = new Date();
        return Jwts.builder()
                .header().type("JWT").and()
                .issuer(jwtProperties.getIssuer())
                .issuedAt(now)
                .expiration(expiry)
                .subject(user.getEmail())
                .claim("id", user.getId())
                .signWith(this.secretKey)
                .compact();
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token){
        return getClaims(token).getSubject();
    }
    public Long getUserId(String token){
        return getClaims(token).get("id", Long.class);
    }
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String createNewAccessToken(RefreshToken refreshToken) throws Exception {
        if (validToken(refreshToken.getRefreshToken())) {
            User user = userService.findById(refreshToken.getUserId());
            String token = generateToken(user, Duration.ofMillis(5));
            return token;
        }else{
            return IllegalAccessException.class.getSimpleName();
        };
    }
}

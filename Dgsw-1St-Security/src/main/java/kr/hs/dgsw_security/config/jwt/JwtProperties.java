package kr.hs.dgsw_security.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
 * jwt:
 *   issuer: dgsw@gmail.com
 *   secret_key: secret
 *   access_expiration_minutes: 15 # 액세스 토큰 만료(분)
 *   refresh_expiration_days: 14 # 리프레시 토큰 만료(일)
 * */

@Configuration
/*
 * .@ConfigurationProperties: application.yml 파일의 property를 가져와서 자바 객체로 만들어주는 어노테이션
 * application.yml 파일을 찾는건 딱히 아니고 주로 루트에 있는 yml 파일 등애서 ("")안의 값으로 묶인 값을 찾아서 연결해주는 것임.
 * 자기들이 알아서 변수도 연결해줌.
 * */
@ConfigurationProperties("jwt")
@Getter
@Setter
public class JwtProperties {
    private String issuer;
    private String secretKey;
    private String accessExpirationMinutes;
    private String refreshExpirationDays;
}
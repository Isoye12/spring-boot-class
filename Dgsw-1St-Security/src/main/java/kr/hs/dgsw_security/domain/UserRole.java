package kr.hs.dgsw_security.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 *enum이란? 상수 열거형
 * */

@Getter
@RequiredArgsConstructor  // 생성자
public enum UserRole {
    // 문자열에 ROLE_을 꼭 붙여줘야 시큐리티가 권한 관련된거라는 것을 알 수 있음.
    // USER[key, 개발자들끼리 쓰는 말](ROLE_USER[value, 스프링 부트 시큐리티와 대화할 때 쓰는 말])
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    private final String key;
}

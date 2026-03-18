package dgsw.hs.kr.intranet.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmployeeRole {
    STAFF("ROLE_STAFF"),
    MANAGER("ROLE_MANAGER"),
    ADMIN("ROLE_ADMIN");

    // 스프링시큐리티 규격에 맞게 변수 앞에 접미사 "ROLE_"이 포함된 키 값
    private final String key;
}

package kr.hs.dgsw.dto;

import kr.hs.dgsw.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private Long id;
    private String email;
    private UserRole role;
}

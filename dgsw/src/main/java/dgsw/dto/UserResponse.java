package dgsw.dto;

import dgsw.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor /* 이건 왜있음 */
public class UserResponse {
    private Long id;
    private String email;
    private UserRole role;
}

package kr.hs.dgsw_security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor /* 이게 왜 필요함거임 */
public class CreateAccessTokenResponse {
    private String accessToken;
}
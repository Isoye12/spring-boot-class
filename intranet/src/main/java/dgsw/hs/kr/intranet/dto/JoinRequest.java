package dgsw.hs.kr.intranet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequest {
    private String empNo;
    private String password;
    private String name;
}

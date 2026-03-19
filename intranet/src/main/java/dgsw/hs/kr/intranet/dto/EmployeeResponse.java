package dgsw.hs.kr.intranet.dto;

import dgsw.hs.kr.intranet.domain.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String empNo;
    private String name;
    private EmployeeRole role;
}

package dgsw.hs.kr.intranet.controller;

import dgsw.hs.kr.intranet.domain.Employee;
import dgsw.hs.kr.intranet.dto.EmployeeResponse;
import dgsw.hs.kr.intranet.dto.JoinRequest;
import dgsw.hs.kr.intranet.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class EmployeeAuthController {
    private final EmployeeService employeeService;

    @PostMapping("/join")
    public ResponseEntity<EmployeeResponse> join (@RequestBody JoinRequest request) {
        Employee employee = employeeService.join(request);
        EmployeeResponse employeeResponse =
                new EmployeeResponse(
                        employee.getId(),
                        employee.getName(),
                        employee.getName(),
                        employee.getRole()
                );
        return ResponseEntity.ok(employeeResponse);
    }
}

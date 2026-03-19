package dgsw.hs.kr.intranet.service;

import dgsw.hs.kr.intranet.domain.Employee;
import dgsw.hs.kr.intranet.domain.EmployeeRole;
import dgsw.hs.kr.intranet.dto.JoinRequest;
import dgsw.hs.kr.intranet.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeSeviceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Employee join(JoinRequest request) {
        return employeeRepository.save(
                Employee.builder()
                        .empNo(request.getEmpNo())
                        .name(request.getName())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(EmployeeRole.STAFF)
                        .build()
        );
    }
}
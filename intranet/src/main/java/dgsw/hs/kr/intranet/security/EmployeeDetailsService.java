package dgsw.hs.kr.intranet.security;

import dgsw.hs.kr.intranet.domain.Employee;
import dgsw.hs.kr.intranet.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String empNo) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmpNo(empNo)
            .orElseThrow(() -> new UsernameNotFoundException("사번을 찾을 수 없습니다." + empNo));

        return new EmployeeDetails(employee);
    }

}

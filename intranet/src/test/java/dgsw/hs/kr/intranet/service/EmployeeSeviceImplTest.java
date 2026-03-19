package dgsw.hs.kr.intranet.service;

import dgsw.hs.kr.intranet.domain.Employee;
import dgsw.hs.kr.intranet.dto.JoinRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class EmployeeSeviceImplTest {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("임직원들 회원가입 및 비밀번호 암호화 테스트")
    void join() {
        // given
        String rawPwd = "pwd12345";
        JoinRequest reqData = new JoinRequest();
        reqData.setName("민채");
        reqData.setPassword(rawPwd);
        reqData.setEmpNo("3101");

        // when
        Employee joinData = employeeService.join(reqData);

        // then
        assertThat(joinData).isNotNull();
        assertThat(joinData.getEmpNo()).isEqualTo("3101");
        assertThat(joinData.getPassword()).isNotEqualTo(rawPwd);
        assertThat(passwordEncoder.matches(rawPwd, joinData.getPassword())).isTrue();
    }
}
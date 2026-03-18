package dgsw.hs.kr.intranet.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@NoArgsConstructor(access = AccessLevel.PROTECTED) /** 기본 생성자가 있어야 오류가 안날거임. */
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "emp_no", nullable = false, unique = true)
    private String empNo;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EmployeeRole role;

    @Builder
    Employee(String empNo, String name, String password, EmployeeRole role) {
        this.empNo = empNo;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}

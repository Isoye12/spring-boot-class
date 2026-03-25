package kr.hs.dgsw_security.domain;

/*
 * 이 클래스는: Entity
 * 테이블 이름: users
 * 변수: email, password, 인가(UserRole)
 * 빌더패턴 사용
 * 빌더패턴? 복잡한 객체의 생성 과정과 표현 방법을 분리하여,
         동일한 생성 절차에서 서로 다른 구성을 가진 객체를 유연하게 만드는 생성 디자인 패턴
 * */

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "pwd", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder  // 얘의 return값이 생성자라서 .찍고 .찍고 .찍고 사용할 수 있다는거임
    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

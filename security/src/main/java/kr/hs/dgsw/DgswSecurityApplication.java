package kr.hs.dgsw;

import kr.hs.dgsw.domain.User;
import kr.hs.dgsw.domain.UserRole;
import kr.hs.dgsw.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DgswSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DgswSecurityApplication.class, args);
    }

}

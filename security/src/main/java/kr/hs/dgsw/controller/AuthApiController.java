package kr.hs.dgsw.controller;

import kr.hs.dgsw.domain.User;
import kr.hs.dgsw.dto.AuthRequest;
import kr.hs.dgsw.dto.AuthResponse;
import kr.hs.dgsw.dto.SignUpRequest;
import kr.hs.dgsw.dto.UserResponse;
import kr.hs.dgsw.service.AuthService;
import kr.hs.dgsw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApiController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request
    ){
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(
            @RequestBody SignUpRequest request
    ) {
        User user = userService.signUp(request);
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
        return ResponseEntity.ok(userResponse);
    }

}

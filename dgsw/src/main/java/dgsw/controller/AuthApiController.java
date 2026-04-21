package dgsw.controller;

import dgsw.domain.User;
import dgsw.dto.SignUpRequest;
import dgsw.dto.UserResponse;
import dgsw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest request) {
        User user = userService.signUp(request);

        UserResponse response = new UserResponse(user.getId(), user.getEmail(), user.getRole());

        return ResponseEntity.ok(response);
    }
}

package kr.hs.dgsw_security.controller;

import kr.hs.dgsw_security.domain.User;
import kr.hs.dgsw_security.dto.SignupRequest;
import kr.hs.dgsw_security.dto.UserResponse;
import kr.hs.dgsw_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController /** Controller + ResponseBody */
@RequestMapping("/api/auth")
public class AuthApiController {
    private final UserService userService;
    /*
     * 1. @RequestBody : body에 json으로 req
     * 2. @ModelAttribute : from을 자바객체로 한 번 받음
     * 3. @RequestParam : get(?a=3), form
     * 4. @PathVariable : /user/1/aaa/2
     * 위에 4개는 꼭 좀 알아두기
     *
     * 5. @RequestHeader : header값 추출할 때
     * 6. @CookieValue : 쿠키는 클라이언트에 저장되는 것임.
     * 7. @RequestPart : 파일 + 데이터 (멀티파트폼데이터의 그 파트)
     * 8. HttpServletRequest :
     * */

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignupRequest req) {
        User user = userService.signUp(req);
        UserResponse userResponse = new UserResponse(user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(userResponse);
    }
}

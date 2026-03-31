package kr.hs.dgsw_security.controller;

import kr.hs.dgsw_security.dto.CreateAccessTokenRequest;
import kr.hs.dgsw_security.dto.CreateAccessTokenResponse;
import kr.hs.dgsw_security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<CreateAccessTokenResponse> createAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(new CreateAccessTokenResponse(newAccessToken));
    }
}
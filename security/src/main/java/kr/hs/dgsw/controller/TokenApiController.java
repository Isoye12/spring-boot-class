package kr.hs.dgsw.controller;

import kr.hs.dgsw.dto.CreateAccessTokenRequest;
import kr.hs.dgsw.dto.CreateAccessTokenResponse;
import kr.hs.dgsw.service.TokenService;
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
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(
            @RequestBody CreateAccessTokenRequest request
    ) throws Exception {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(
                new CreateAccessTokenResponse(newAccessToken)
        );
    }
}

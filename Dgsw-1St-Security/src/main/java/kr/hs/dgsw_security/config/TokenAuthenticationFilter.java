package kr.hs.dgsw_security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.dgsw_security.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component /* 콩 유무로 런타임 오류가 뜸 */
/* OncePerRequestFilter - 요청 한 번 당 한 번 싱행돔 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final String HEADER_AUTH_STR = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getAccessToken(request.getHeader(HEADER_AUTH_STR));

        if(StringUtils.hasText(token) && tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String headerAuthStr) {
        if (headerAuthStr != null && headerAuthStr.startsWith(BEARER_PREFIX)) {
            return headerAuthStr
                    .substring(BEARER_PREFIX.length()) /* substring - 원하는 지점(구간)의 연속된 숫자를 획득 */
                    .trim();
        }
        return null;
    }
}

package kr.hs.dgsw_security.security;

import kr.hs.dgsw_security.domain.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;
    private Collection<? extends GrantedAuthority> authorities;

    // private int loginFailCount = 0;

    public CustomUserDetails(User user) {
        this.user = user;
        this.authorities = List.of(new SimpleGrantedAuthority(user.getRole().getKey()));
    }
    /* SimpleGrantedAuthority: string으로 값을 주면 권한을 설정해주는거 (USER string 주면, USER 권한으로 해석) */

    /*
     * 사용자가 가지고 있는 권한 목록을 반환
     * 생성자에서 미리 만들어둔 권한을 반환한다
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /* 비밀번호 리턴 + 암호화 */
    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    /* 유저 이름 리턴 */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /*
     * 계정 만료되었니? true: 만료아님
     * 구독 서비스에서 사용하면 되겠음
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /* 계정이 잠겼니? ture: 아니 */
    @Override
    public boolean isAccountNonLocked() {
        // return loginCountFail < 5;
        return true;
        }

    /* 비밀번호가 만료됐니? ture: 아니 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /* 사용가능하니? ture: 어 */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
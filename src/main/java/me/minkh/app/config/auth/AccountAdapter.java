package me.minkh.app.config.auth;

import lombok.Getter;
import me.minkh.app.domain.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class AccountAdapter implements OAuth2User, UserDetails {

    private final Account account;
    private OAuth2User oAuth2User;

    public AccountAdapter(Account account) {
        this.account = account;
    }

    public AccountAdapter(Account account, OAuth2User oAuth2User) {
        this.account = account;
        this.oAuth2User = oAuth2User;
    }

    // OAuth2User

    @Override
    public Map<String, Object> getAttributes() {
        return this.oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return this.oAuth2User.getAttribute("name");
    }

    // UserDetails

    @Override
    public String getPassword() {
        return this.account.getPassword();
    }

    @Override
    public String getUsername() {
        return this.account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

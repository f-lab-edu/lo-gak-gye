package me.minkh.app.config.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.minkh.app.config.ConfigConst;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.dto.account.Attribute;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyOAuth2UserService extends DefaultOAuth2UserService {

    private final AccountRepository accountRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 계정이 없을 경우 저장, email 혹은 name 이 수정되면 변경 후 저장
        Attribute attribute = new Attribute(oAuth2User.getAttributes());
        Account account = this.accountRepository.findByEmail(attribute.getEmail())
                .map(entity -> entity.update(attribute.getEmail(), attribute.getName()))
                .orElse(attribute.toEntity());
        this.accountRepository.save(account);

        // 세션 저장, ID만 저장한 뒤, 필요하면 꺼내서 쓴다.
        httpSession.setAttribute(ConfigConst.SESSION, account.getId());

        return oAuth2User;
    }
}

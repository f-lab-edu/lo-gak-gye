package me.minkh.app.service.account;

import lombok.RequiredArgsConstructor;
import me.minkh.app.config.auth.AccountAdapter;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.dto.account.AccountRequest;
import me.minkh.app.dto.account.AccountResponse;
import me.minkh.app.dto.account.UpdateApiKeyRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String INVALID_REQUEST_MESSAGE = "은 올바르지 않은 요청입니다.";

    public AccountResponse save(AccountRequest accountRequest) {
        accountRequest.encodePassword(this.passwordEncoder);
        Account savedAccount = accountRepository.save(accountRequest.toEntity());
        return new AccountResponse(savedAccount);
    }

    public AccountResponse findById(Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + INVALID_REQUEST_MESSAGE));
        return new AccountResponse(account);
    }

    public AccountResponse updateApiKey(UpdateApiKeyRequest request, Long id) {
        String apiKey = request.getApiKey();
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + INVALID_REQUEST_MESSAGE));
        return new AccountResponse(account.updateApiKey(apiKey));
    }

    @Override
    public AccountAdapter loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + INVALID_REQUEST_MESSAGE));
        return new AccountAdapter(account);
    }
}

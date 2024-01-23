package me.minkh.app.service.account;

import lombok.RequiredArgsConstructor;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.dto.account.AccountRequest;
import me.minkh.app.dto.account.AccountResponse;
import me.minkh.app.dto.account.UpdateApiKeyRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public Account save(AccountRequest accountRequest) {
        return accountRequest.toEntity();
    }

    public AccountResponse findById(Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "은 올바르지 않은 요청입니다."));
        return new AccountResponse(account);
    }

    public AccountResponse updateApiKey(UpdateApiKeyRequest request, Long id) {
        String apiKey = request.getApiKey();
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "은 올바르지 않은 요청입니다."));
        return new AccountResponse(account.updateApiKey(apiKey));
    }
}

package me.minkh.app.service.account;

import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.dto.account.AccountResponse;
import me.minkh.app.dto.account.UpdateApiKeyRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class AccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @AfterEach
    void afterEach() {
        this.accountRepository.deleteAll();
    }

    @DisplayName("API Key 업데이트 성공 테스트")
    @Test
    void updateApiKeySuccess() {
        // given
        String apiKey = UUID.randomUUID().toString();
        UpdateApiKeyRequest request = UpdateApiKeyRequest
                .builder()
                .apiKey(apiKey)
                .build();

        Account savedAccount = accountRepository.save(Account.builder()
                .name("test")
                .email("test@test.com")
                .apiKey(apiKey)
                .build());

        // when
        AccountResponse accountResponse = accountService.updateApiKey(request, savedAccount.getId());

        // then
        assertThat(accountResponse.getId()).isEqualTo(savedAccount.getId());
        assertThat(accountResponse.getEmail()).isEqualTo(savedAccount.getEmail());
        assertThat(accountResponse.getName()).isEqualTo(savedAccount.getName());
        assertThat(accountResponse.getApiKey()).isEqualTo(savedAccount.getApiKey());
    }

    @DisplayName("API Key 업데이트 실패 테스트")
    @Test
    void updateApiKeyFail() {
        // given
        UpdateApiKeyRequest request = UpdateApiKeyRequest
                .builder()
                .apiKey(UUID.randomUUID().toString())
                .build();

        // when & then
        assertThatThrownBy(() -> accountService.updateApiKey(request, 1L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
package me.minkh.app.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.minkh.app.domain.account.Account;

@AllArgsConstructor
@Getter
public class AccountResponse {

    private final Long id;

    private final String email;

    private final String name;

    private final String apiKey;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.name = account.getName();
        this.apiKey = account.getApiKey();
    }
}

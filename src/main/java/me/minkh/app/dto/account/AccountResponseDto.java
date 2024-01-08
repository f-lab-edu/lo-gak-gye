package me.minkh.app.dto.account;

import lombok.Getter;
import me.minkh.app.domain.account.Account;

@Getter
public class AccountResponseDto {

    private final Long id;

    private final String name;

    private final String apiKey;

    public AccountResponseDto(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.apiKey = account.getApiKey();
    }
}

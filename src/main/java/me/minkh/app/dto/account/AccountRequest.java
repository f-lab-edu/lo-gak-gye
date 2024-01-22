package me.minkh.app.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import me.minkh.app.domain.account.Account;

@Getter
public class AccountRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    private String apiKey;

    public Account toEntity() {
        return Account.builder()
                .name(this.name)
                .email(this.email)
                .apiKey(this.apiKey)
                .build();
    }
}

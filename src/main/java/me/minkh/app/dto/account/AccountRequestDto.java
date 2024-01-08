package me.minkh.app.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import me.minkh.app.domain.account.Account;

@AllArgsConstructor
@ToString
@Getter
public class AccountRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String apiKey;

    public Account toEntity() {
        return Account.builder()
                .name(this.name)
                .apiKey(this.apiKey)
                .build();
    }
}

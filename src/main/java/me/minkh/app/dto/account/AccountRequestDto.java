package me.minkh.app.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import me.minkh.app.domain.account.Account;

@AllArgsConstructor
@ToString
@Getter
public class AccountRequestDto {

    private String name;

    private String apiKey;

    public Account toEntity() {
        return Account.builder()
                .name(this.name)
                .apiKey(this.apiKey)
                .build();
    }
}

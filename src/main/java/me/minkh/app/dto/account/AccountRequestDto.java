package me.minkh.app.dto.account;

import lombok.Getter;
import lombok.ToString;
import me.minkh.app.domain.account.Account;

@ToString
@Getter
public class AccountRequestDto {

    private String name;

    private String key;

    public AccountRequestDto() {
    }

    public AccountRequestDto(String name) {
        this.name = name;
    }

    public Account toEntity() {
        return Account.builder()
                .name(this.name)
                .key((this.key))
                .build();
    }
}

package me.minkh.app.dto.account;

import lombok.Getter;
import me.minkh.app.domain.account.Account;

import java.util.Map;

@Getter
public class Attribute {

    private final String name;

    private final String email;

    public Attribute(Map<String, Object> attributes) {
        this.name = (String) attributes.get("name");
        this.email = (String) attributes.get("email");
    }

    public Account toEntity() {
        return Account.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}

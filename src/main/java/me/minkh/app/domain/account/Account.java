package me.minkh.app.domain.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minkh.app.domain.model.BaseTimeEntity;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Account extends BaseTimeEntity {

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @Column(columnDefinition = "TEXT")
    private String apiKey;

    private boolean isEmailVerified;

    public Account update(String email, String name) {
        this.email = email;
        this.name = name;
        return this;
    }

    public Account updateApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public void verifiedEmail() {
        this.isEmailVerified = true;
    }
}


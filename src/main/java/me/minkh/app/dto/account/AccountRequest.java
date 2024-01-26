package me.minkh.app.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.minkh.app.domain.account.Account;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Builder
@Getter
public class AccountRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public Account toEntity() {
        return Account.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}

package me.minkh.app.dto.account.verificationcode;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class VerificationCodeCheckRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String code;
}



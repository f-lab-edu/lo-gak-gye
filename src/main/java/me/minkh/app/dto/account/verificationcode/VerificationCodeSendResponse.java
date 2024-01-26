package me.minkh.app.dto.account.verificationcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.minkh.app.domain.account.VerificationCode;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class VerificationCodeSendResponse {

    private final String code;

    private final String email;

    private final LocalDateTime expiredDate;

    public VerificationCodeSendResponse(VerificationCode verificationCode) {
        this.code = verificationCode.getCode();
        this.email = verificationCode.getAccount().getEmail();
        this.expiredDate = verificationCode.getExpiredDate();
    }
}



package me.minkh.app.dto.account.verificationcode;

import lombok.Getter;

@Getter
public class VerificationCodeCheckResponse {

    boolean success;

    public VerificationCodeCheckResponse(boolean success) {
        this.success = success;
    }
}



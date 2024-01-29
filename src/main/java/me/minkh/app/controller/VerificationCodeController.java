package me.minkh.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.account.verificationcode.VerificationCodeCheckRequest;
import me.minkh.app.dto.account.verificationcode.VerificationCodeCheckResponse;
import me.minkh.app.dto.account.verificationcode.VerificationCodeSendRequest;
import me.minkh.app.dto.account.verificationcode.VerificationCodeSendResponse;
import me.minkh.app.service.account.VerificationCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/verification-code")
public class VerificationCodeController {

    private final VerificationCodeService verificationCodeService;

    @PostMapping("/send")
    public VerificationCodeSendResponse send(@Valid @RequestBody VerificationCodeSendRequest request) {
        return this.verificationCodeService.send(request);
    }

    @PostMapping("/check")
    public VerificationCodeCheckResponse check(@Valid @RequestBody VerificationCodeCheckRequest request) {
        return this.verificationCodeService.check(request);
    }
}

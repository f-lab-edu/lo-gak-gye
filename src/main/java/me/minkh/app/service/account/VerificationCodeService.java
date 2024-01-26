package me.minkh.app.service.account;

import lombok.RequiredArgsConstructor;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.domain.account.VerificationCode;
import me.minkh.app.domain.account.VerificationCodeRepository;
import me.minkh.app.dto.account.verificationcode.VerificationCodeCheckRequest;
import me.minkh.app.dto.account.verificationcode.VerificationCodeCheckResponse;
import me.minkh.app.dto.account.verificationcode.VerificationCodeSendRequest;
import me.minkh.app.dto.account.verificationcode.VerificationCodeSendResponse;
import me.minkh.app.service.MailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class VerificationCodeService {

    private final MailService mailService;
    private final AccountRepository accountRepository;
    private final VerificationCodeRepository verificationCodeRepository;

    @Transactional
    public VerificationCodeSendResponse send(VerificationCodeSendRequest request) {
        String email = request.getEmail();
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email + "은 올바르지 않은 요청입니다."));

        // 인증번호를 생성하고, 이메일을 전송한다.
        String code = getCode();
        this.mailService.sendEmail(email, code);

        // 인증번호 전용 테이블에 인증번호를 넣는다. 유효 시간은 3분이다.
        LocalDateTime expiredDate = LocalDateTime.now().plusMinutes(3);

        // 인증번호가 있으면? 갱신한 것이다. 인증번호가 없으면? 새로 등록하는 것이다.
        VerificationCode verificationCode = verificationCodeRepository.findByAccount(account)
                .orElse(new VerificationCode());

        verificationCode.update(code, account, expiredDate);
        return new VerificationCodeSendResponse(verificationCodeRepository.save(verificationCode));
    }

    @Transactional
    public VerificationCodeCheckResponse check(VerificationCodeCheckRequest request) {
        String email = request.getEmail();
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email + "은 올바르지 않은 요청입니다."));

        VerificationCode verificationCode = verificationCodeRepository.findByAccount(account)
                .orElseThrow(() -> new IllegalArgumentException("인증번호를 재발급 받으세요."));

        // 코드가 일치하지 않을 때,
        if (verificationCode.isCodeMisMatch(request.getCode())) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }

        // 유효기간이 만료됐을 때,
        if (verificationCode.isExpired()) {
            throw new IllegalArgumentException("유효기간이 만료된 인증번호 입니다.");
        }

        account.verifiedEmail();
        return new VerificationCodeCheckResponse(true);
    }

    private String getCode() {
        int num = 100_000 + new Random().nextInt(900_000);
        return String.valueOf(num);
    }
}

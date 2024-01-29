package me.minkh.app.service.account;

import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.domain.account.VerificationCode;
import me.minkh.app.domain.account.VerificationCodeRepository;
import me.minkh.app.dto.account.verificationcode.VerificationCodeCheckRequest;
import me.minkh.app.dto.account.verificationcode.VerificationCodeCheckResponse;
import me.minkh.app.dto.account.verificationcode.VerificationCodeSendRequest;
import me.minkh.app.dto.account.verificationcode.VerificationCodeSendResponse;
import me.minkh.app.service.MailService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@ActiveProfiles("test")
class VerificationCodeServiceTest {

    @Autowired
    VerificationCodeService verificationCodeService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @AfterEach
    void afterEach() {
        verificationCodeRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @MockBean
    MailService mailService;

    @DisplayName("Send 성공 테스트")
    @Test
    void send() {
        // given
        Account account = saveAccount();
        VerificationCodeSendRequest request = VerificationCodeSendRequest.builder()
                .email(account.getEmail())
                .build();

        // when
        VerificationCodeSendResponse response = this.verificationCodeService.send(request);
        doNothing().when(mailService).sendEmail(anyString(), anyString());

        // then
        assertThat(response.getEmail()).isEqualTo(request.getEmail());
        assertThat(response.getCode().length()).isEqualTo(8);
        assertThat(response.getExpiredDate()).isAfter(LocalDateTime.now());
    }

    @DisplayName("Send 실패 테스트")
    @Test
    void sendFail() {
        // given
        VerificationCodeSendRequest request = VerificationCodeSendRequest.builder()
                .email("fail@tset.com")
                .build();

        // when & then
        Assertions.assertThatThrownBy(() -> this.verificationCodeService.send(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Check 성공 테스트")
    @Test
    void check() {
        // given
        Account account = saveAccount();
        VerificationCode verificationCode = saveVerificationCode(account, LocalDateTime.now().plusMinutes(3));
        VerificationCodeCheckRequest request = VerificationCodeCheckRequest.builder()
                .email(account.getEmail())
                .code(verificationCode.getCode())
                .build();

        // when
        VerificationCodeCheckResponse response = verificationCodeService.check(request);
        Account findAccount = accountRepository.findByEmail(account.getEmail()).orElseThrow();

        // then
        assertThat(findAccount.isEmailVerified()).isTrue();
        assertThat(response.isSuccess()).isTrue();
    }

    @DisplayName("Check 실패 테스트1 - Account 없음")
    @Test
    void checkFail1() {
        // given
        VerificationCodeCheckRequest request = VerificationCodeCheckRequest.builder()
                .email("test@test.com")
                .code("1234567")
                .build();

        // when & then
        assertThatThrownBy(() -> verificationCodeService.check(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Check 실패 테스트2 - VerificationCode 없음")
    @Test
    void checkFail2() {
        // given
        Account account = saveAccount();
        VerificationCodeCheckRequest request = VerificationCodeCheckRequest.builder()
                .email(account.getEmail())
                .code("123456")
                .build();

        // when & then
        assertThatThrownBy(() -> verificationCodeService.check(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Check 실패 테스트3 - 코드가 일치하지 않음")
    @Test
    void checkFail3() {
        // given
        Account account = saveAccount();
        VerificationCode verificationCode = saveVerificationCode(account, LocalDateTime.now().plusMinutes(3));
        VerificationCodeCheckRequest request = VerificationCodeCheckRequest.builder()
                .email(account.getEmail())
                .code(verificationCode.getCode() + "0")
                .build();

        // when & then
        assertThatThrownBy(() -> verificationCodeService.check(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Check 실패 테스트4 - 유효기간 만료")
    @Test
    void checkFail4() {
        // given
        Account account = saveAccount();
        VerificationCode verificationCode = saveVerificationCode(account, LocalDateTime.now());
        VerificationCodeCheckRequest request = VerificationCodeCheckRequest.builder()
                .email(account.getEmail())
                .code(verificationCode.getCode())
                .build();

        // when & then
        assertThatThrownBy(() -> verificationCodeService.check(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Account saveAccount() {
        return accountRepository.save(
                Account.builder()
                        .email("test@test.com")
                        .name("test")
                        .password("password")
                        .build()
        );
    }

    private VerificationCode saveVerificationCode(Account account, LocalDateTime expiredDate) {
        return verificationCodeRepository.save(
                VerificationCode.builder()
                        .code("12345678")
                        .expiredDate(expiredDate)
                        .account(account)
                        .build()
        );
    }
}
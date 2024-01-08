package me.minkh.app.controller;

import me.minkh.app.domain.account.Account;
import me.minkh.app.dto.account.AccountRequestDto;
import me.minkh.app.dto.account.AccountResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AccountControllerTest {

    @Autowired
    AccountController accountController;

    @Test
    void test() {
        AccountRequestDto dto = new AccountRequestDto("test", "ABCDEFGHIJKLMNOP");
        Account account = accountController.save(dto);

        assertThat(account.getId()).isEqualTo(1L);
        assertThat(account.getName()).isEqualTo("test");

        AccountResponseDto responseDto = accountController.findById(account.getId());
        assertThat(responseDto.getId()).isEqualTo(1L);
        assertThat(responseDto.getName()).isEqualTo("test");
    }

}
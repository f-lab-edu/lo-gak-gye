package me.minkh.app.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.dto.account.AccountRequestDto;
import me.minkh.app.dto.account.AccountResponseDto;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public Account save(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        return this.accountRepository.save(accountRequestDto.toEntity());
    }

    @GetMapping("/{id}")
    public AccountResponseDto findById(@PathVariable("id") Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "은 올바르지 않은 요청입니다."));
        return new AccountResponseDto(account);
    }
}

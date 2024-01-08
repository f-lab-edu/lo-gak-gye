package me.minkh.app.controller;

import lombok.extern.slf4j.Slf4j;
import me.minkh.app.exception.ErrorResponse;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.dto.account.AccountRequestDto;
import me.minkh.app.dto.account.AccountResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public Account save(@RequestBody AccountRequestDto accountRequestDto) {
        return this.accountRepository.save(accountRequestDto.toEntity());
    }

    @GetMapping("/{id}")
    public AccountResponseDto findById(@PathVariable("id") Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "은 올바르지 않은 요청입니다."));
        return new AccountResponseDto(account);
    }
}

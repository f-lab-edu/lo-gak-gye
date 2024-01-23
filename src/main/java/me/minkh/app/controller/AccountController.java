package me.minkh.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.minkh.app.config.ConfigConst;
import me.minkh.app.domain.account.Account;
import me.minkh.app.dto.account.AccountRequest;
import me.minkh.app.dto.account.AccountResponse;
import me.minkh.app.dto.account.UpdateApiKeyRequest;
import me.minkh.app.service.account.AccountService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public Account save(@Valid @RequestBody AccountRequest accountRequest) {
        return this.accountService.save(accountRequest);
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable("id") Long id) {
        return this.accountService.findById(id);
    }

    @PatchMapping
    public AccountResponse updateApiKey(
            @Valid @RequestBody UpdateApiKeyRequest request,
            @SessionAttribute(ConfigConst.SESSION) Long id) {
        return this.accountService.updateApiKey(request, id);
    }
}

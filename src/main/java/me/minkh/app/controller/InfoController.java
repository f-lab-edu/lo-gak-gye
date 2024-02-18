package me.minkh.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.minkh.app.config.auth.CurrentId;
import me.minkh.app.dto.info.InfoResponse;
import me.minkh.app.exception.TooManyRequestsException;
import me.minkh.app.service.RateLimitService;
import me.minkh.app.service.info.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;
    private final RateLimitService rateLimitService;

    @GetMapping("/info/{characterName}")
    public InfoResponse info(
            @PathVariable("characterName") String characterName,
            @CurrentId Long accountId
    ) {
        if (rateLimitService.isLimit(accountId.toString())) {
            throw new TooManyRequestsException("너무 많은 요청을 보냈습니다. 잠시 대기해 주세요.");
        }
        return this.infoService.info(characterName, accountId);
    }
}

package me.minkh.app.controller;

import lombok.extern.slf4j.Slf4j;
import me.minkh.app.config.ConfigConst;
import me.minkh.app.dto.info.InfoResponse;
import me.minkh.app.service.info.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService homeService) {
        this.infoService = homeService;
    }

    @GetMapping("/info/{characterName}")
    public InfoResponse info(
            @PathVariable("characterName") String characterName,
            @SessionAttribute(ConfigConst.SESSION) Long accountId
    ) {
        return this.infoService.info(characterName, accountId);
    }
}

package me.minkh.app.controller;

import me.minkh.app.dto.info.InfoResponse;
import me.minkh.app.service.info.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService homeService) {
        this.infoService = homeService;
    }

    @GetMapping("/info/{characterName}")
    public InfoResponse info(@PathVariable("characterName") String characterName) {
        return this.infoService.info(characterName);
    }
}

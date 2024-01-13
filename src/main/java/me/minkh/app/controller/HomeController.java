package me.minkh.app.controller;

import lombok.extern.slf4j.Slf4j;
import me.minkh.app.dto.info.InfoResponseDto;
import me.minkh.app.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/info/{characterName}")
    public InfoResponseDto info(@PathVariable("characterName") String characterName) {
        return this.homeService.info(characterName);
    }
}

package me.minkh.app.controller;

import me.minkh.app.dto.lostark.ProfileDto;
import me.minkh.app.service.LostArkApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final LostArkApiService lostArkApiService;

    public HomeController(LostArkApiService lostArkApiService) {
        this.lostArkApiService = lostArkApiService;
    }

    @GetMapping("/info/{characterName}")
    public ProfileDto getProfiles(@PathVariable("characterName") String characterName) {
        return this.lostArkApiService.getProfiles(characterName);
    }
}

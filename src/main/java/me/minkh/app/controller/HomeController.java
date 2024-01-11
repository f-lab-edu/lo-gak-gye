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

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    // 이미지 빌드 체크용 메서드
    @GetMapping("/check")
    public String check() {
        int num = 3;
        return "check" + num;
    }

    @GetMapping("/info/{characterName}")
    public ProfileDto getProfiles(@PathVariable("characterName") String characterName) {
        return this.lostArkApiService.getProfiles(characterName);
    }
}

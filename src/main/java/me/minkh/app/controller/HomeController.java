package me.minkh.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

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
        int num = 1;
        return "check" + num;
    }
}

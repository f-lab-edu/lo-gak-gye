package me.minkh.app.controller;

import lombok.extern.slf4j.Slf4j;
import me.minkh.app.dto.info.InfoResponseDto;
import me.minkh.app.dto.lostark.EngravingsDto;
import me.minkh.app.dto.lostark.EquipmentDto;
import me.minkh.app.dto.lostark.ProfileDto;
import me.minkh.app.service.LostArkApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {

    private final LostArkApiService lostArkApiService;

    public HomeController(LostArkApiService lostArkApiService) {
        this.lostArkApiService = lostArkApiService;
    }

    @GetMapping("/info/{characterName}")
    public InfoResponseDto getProfiles(@PathVariable("characterName") String characterName) {
        EquipmentDto[] equipmentDto = this.lostArkApiService.getEquipment(characterName); // artifact 추출

        ProfileDto profileDto = this.lostArkApiService.getProfiles(characterName); // 치명, 신속 추출

        EngravingsDto engravings = this.lostArkApiService.getEngravings(characterName); // cursedDoll, adrenaline 추출

        return null;
    }
}

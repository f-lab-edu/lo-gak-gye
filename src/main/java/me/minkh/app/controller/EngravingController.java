package me.minkh.app.controller;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.engraving.request.EngravingSetupRequest;
import me.minkh.app.dto.engraving.response.EngravingCalcResponse;
import me.minkh.app.dto.engraving.response.EngravingPresetResponse;
import me.minkh.app.service.engraving.EngravingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/engravings")
public class EngravingController {

    private final EngravingService engravingService;

    @PostMapping("/calc")
    public List<EngravingCalcResponse> calcEngravings(@RequestBody EngravingSetupRequest request) {
        return this.engravingService.calcEngravings(request);
    }

    @PostMapping("/presets")
    public EngravingPresetResponse savePreset(@RequestBody EngravingSetupRequest request) {
        return this.engravingService.savePreset(request);
    }
}
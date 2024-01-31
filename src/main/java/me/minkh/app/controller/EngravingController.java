package me.minkh.app.controller;

import lombok.RequiredArgsConstructor;
import me.minkh.app.config.auth.CurrentId;
import me.minkh.app.dto.engraving.request.EngravingSetupRequest;
import me.minkh.app.dto.engraving.response.EngravingCalcResponse;
import me.minkh.app.dto.engraving.response.EngravingPresetResponse;
import me.minkh.app.service.engraving.EngravingService;
import org.springframework.web.bind.annotation.*;

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
    public EngravingPresetResponse savePreset(
            @RequestBody EngravingSetupRequest request,
            @CurrentId Long accountId
    ) {
        return this.engravingService.savePreset(request, accountId);
    }

    @GetMapping("/presets")
    public List<EngravingPresetResponse> getPresets(@CurrentId Long accountId) {
        return this.engravingService.getPresets(accountId);
    }

    @GetMapping("/presets/{presetId}")
    public EngravingPresetResponse getPreset(@PathVariable("presetId") Long presetId, @CurrentId Long accountId) {
        return this.engravingService.getPreset(presetId, accountId);
    }
}
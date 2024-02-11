package me.minkh.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import me.minkh.app.config.auth.CurrentId;
import me.minkh.app.dto.engraving.request.EngravingSetupRequest;
import me.minkh.app.dto.engraving.response.EngravingCalcResponse;
import me.minkh.app.dto.engraving.response.EngravingPresetResponse;
import me.minkh.app.service.CacheService;
import me.minkh.app.service.engraving.EngravingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/engravings")
public class EngravingController {

    private final EngravingService engravingService;
    private final CacheService cacheService;

    @PostMapping("/calc")
    public List<EngravingCalcResponse> calcEngravings(@RequestBody EngravingSetupRequest request) throws JsonProcessingException {
        String cacheKey = request.toString();

        List<EngravingCalcResponse> cachedResponse = this.cacheService.getCache(cacheKey, new TypeReference<List<EngravingCalcResponse>>() {});
        // 캐시된 값이 있으면 반환
        if (cachedResponse != null) {
            return cachedResponse;
        }

        // 캐시된 값이 없으면 실제 계산 수행
        List<EngravingCalcResponse> response = engravingService.calcEngravings(request);
        cacheService.setCache(cacheKey, response);

        return response;
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

    @DeleteMapping("/presets/{presetId}")
    public EngravingPresetResponse deletePreset(@PathVariable("presetId") Long presetId, @CurrentId Long accountId) {
        return this.engravingService.deletePreset(presetId, accountId);
    }
}
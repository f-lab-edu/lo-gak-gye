package me.minkh.app.controller;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.engraving.request.EngravingSetupRequest;
import me.minkh.app.dto.engraving.response.EngravingCalcResponse;
import me.minkh.app.service.engraving.EngravingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/engravings")
public class EngravingController {

    private final EngravingService engravingService;

    @PostMapping("/calc")
    public List<EngravingCalcResponse> calcEngravings(@RequestBody EngravingSetupRequest requestDto) {
        return this.engravingService.calcEngravings(requestDto);
    }
}
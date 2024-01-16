package me.minkh.app.controller;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.engraving.request.CalcEngravingRequest;
import me.minkh.app.dto.engraving.response.CalcEngravingResponse;
import me.minkh.app.service.engraving.EngravingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EngravingController {

    private final EngravingService engravingService;

    @PostMapping("/calc/engravings")
    public List<CalcEngravingResponse> calcEngravings(@RequestBody CalcEngravingRequest requestDto) {
        return this.engravingService.calcEngravings(requestDto);
    }
}
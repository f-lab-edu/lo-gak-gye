package me.minkh.app.controller;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.engraving.CalcEngravingRequestDto;
import me.minkh.app.dto.engraving.CalcEngravingResponseDto;
import me.minkh.app.service.engraving.EngravingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EngravingController {

    private final EngravingService engravingService;

    @PostMapping("/calc/engravings")
    public List<CalcEngravingResponseDto> calcEngravings(@RequestBody CalcEngravingRequestDto requestDto) {
        return this.engravingService.calcEngravings(requestDto);
    }
}
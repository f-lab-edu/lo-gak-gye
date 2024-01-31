package me.minkh.app.service.engraving.converter;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.dto.engraving.request.EngravingDto;
import me.minkh.app.service.engraving.converter.strategy.engravings.EngravingsConverterStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EngravingsConverter {

    private final List<EngravingsConverterStrategy> strategies;

    public CombatAttributeDto convert(List<EngravingDto> engravings) {
        CombatAttributeDto result = new CombatAttributeDto();
        for (EngravingDto engraving : engravings) {
            for (EngravingsConverterStrategy strategy : this.strategies) {
                if (!strategy.supports(engraving.getName())) continue;
                strategy.updateCombatAttributeDto(result, engraving.getLevel());
            }
        }
        return result;
    }
}
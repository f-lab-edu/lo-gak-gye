package me.minkh.app.service.engraving.converter;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.dto.engraving.request.CombatStat;
import me.minkh.app.service.engraving.converter.strategy.combatstats.CombatStatsConverterStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CombatStatsConverter {

    private final List<CombatStatsConverterStrategy> strategies;

    public CombatAttributeDto convert(List<CombatStat> combatStats) {
        CombatAttributeDto result = new CombatAttributeDto();
        for (CombatStat combatStat : combatStats) {
            for (CombatStatsConverterStrategy strategy : strategies) {
                if (!strategy.supports(combatStat.getType())) continue;
                strategy.updateCombatAttributeDto(result, combatStat.getValue());
            }
        }
        return result;
    }
}

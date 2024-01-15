package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.dto.engraving.request.CombatStat;
import me.minkh.app.service.engraving.converter.strategy.combatstats.CombatStatsConverterStrategy;
import me.minkh.app.service.engraving.converter.strategy.combatstats.CriticalStrategy;
import me.minkh.app.service.engraving.converter.strategy.combatstats.SwiftnessStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.minkh.app.service.LostArkConstants.CRITICAL;
import static me.minkh.app.service.LostArkConstants.SWIFTNESS;

@Service
public class CombatStatsConverter {

    private final Map<String, CombatStatsConverterStrategy> strategyContext = new HashMap<>();

    public CombatStatsConverter() {
        strategyContext.put(CRITICAL, new CriticalStrategy());
        strategyContext.put(SWIFTNESS, new SwiftnessStrategy());
    }

    public CombatAttributeDto convert(List<CombatStat> combatStats) {
        CombatAttributeDto result = new CombatAttributeDto();
        for (CombatStat combatStat : combatStats) {
            String type = combatStat.getType();
            int value = combatStat.getValue();

            CombatStatsConverterStrategy strategy = strategyContext.get(type);
            if (strategy == null) continue;

            strategy.updateCombatAttributeDto(result, value);
        }
        return result;
    }
}

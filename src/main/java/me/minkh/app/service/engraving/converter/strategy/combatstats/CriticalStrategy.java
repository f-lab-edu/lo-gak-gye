package me.minkh.app.service.engraving.converter.strategy.combatstats;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.springframework.stereotype.Component;

import static me.minkh.app.service.LostArkConstants.CRITICAL;
import static me.minkh.app.service.LostArkConstants.CRITICAL_HIT_RATE_COEFFICIENT;

@Component
public class CriticalStrategy implements CombatStatsConverterStrategy {

    @Override
    public boolean supports(String type) {
        return type.equals(CRITICAL);
    }

    public void updateCombatAttributeDto(CombatAttributeDto dto, int value) {
        double criticalHitRate = value * CRITICAL_HIT_RATE_COEFFICIENT;
        dto.setCriticalHitRate(criticalHitRate);
    }
}

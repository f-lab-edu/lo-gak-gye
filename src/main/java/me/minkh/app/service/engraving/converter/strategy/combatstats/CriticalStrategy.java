package me.minkh.app.service.engraving.converter.strategy.combatstats;

import me.minkh.app.dto.engraving.CombatAttributeDto;

import static me.minkh.app.service.LostArkConstants.CRITICAL_HIT_RATE_COEFFICIENT;

public class CriticalStrategy implements CombatStatsConverterStrategy {

    public void updateCombatAttributeDto(CombatAttributeDto dto, int value) {
        double criticalHitRate = value * CRITICAL_HIT_RATE_COEFFICIENT;
        dto.setCriticalHitRate(criticalHitRate);
    }
}

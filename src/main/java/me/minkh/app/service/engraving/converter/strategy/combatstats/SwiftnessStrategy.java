package me.minkh.app.service.engraving.converter.strategy.combatstats;

import me.minkh.app.dto.engraving.CombatAttributeDto;

import static me.minkh.app.service.LostArkConstants.SPEED_INCREASE_COEFFICIENT;

public class SwiftnessStrategy implements CombatStatsConverterStrategy {

    public void updateCombatAttributeDto(CombatAttributeDto dto, int value) {
        double speedIncrease = value * SPEED_INCREASE_COEFFICIENT / 100;
        dto.setSpeedIncrease(speedIncrease);
    }
}

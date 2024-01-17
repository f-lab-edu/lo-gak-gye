package me.minkh.app.service.engraving.converter.strategy.combatstats;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.springframework.stereotype.Component;

import static me.minkh.app.service.LostArkConstants.SPEED_INCREASE_COEFFICIENT;
import static me.minkh.app.service.LostArkConstants.SWIFTNESS;

@Component
public class SwiftnessStrategy implements CombatStatsConverterStrategy {

    @Override
    public boolean supports(String type) {
        return type.equals(SWIFTNESS);
    }

    public void updateCombatAttributeDto(CombatAttributeDto dto, int value) {
        double speedIncrease = value * SPEED_INCREASE_COEFFICIENT / 100;
        dto.setSpeedIncrease(speedIncrease);
    }
}

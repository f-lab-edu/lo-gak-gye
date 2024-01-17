package me.minkh.app.service.engraving.converter.strategy.engravings;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.springframework.stereotype.Component;

import static me.minkh.app.service.LostArkConstants.*;

@Component
public class AdrenalineStrategy implements EngravingsConverterStrategy {

    @Override
    public boolean supports(String name) {
        return name.equals(ADRENALINE);
    }

    @Override
    public void updateCombatAttributeDto(CombatAttributeDto dto, int level) {
        dto.setAttackIncrease(dto.getAttackIncrease() + ADRENALINE_TO_ATTACK_INCREASE_MAP.get(level));
        dto.setCriticalHitRate(ADRENALINE_TO_CRITICAL_HIT_RATE_MAP.get(level));
    }
}

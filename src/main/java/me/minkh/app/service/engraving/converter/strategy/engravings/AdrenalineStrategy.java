package me.minkh.app.service.engraving.converter.strategy.engravings;

import me.minkh.app.dto.engraving.CombatAttributeDto;

import static me.minkh.app.service.LostArkConstants.*;

public class AdrenalineStrategy implements EngravingsConverterStrategy {

    @Override
    public void updateCombatAttributeDto(CombatAttributeDto dto, int level) {
        dto.setAttackIncrease(dto.getAttackIncrease() + ADRENALINE_TO_ATTACK_INCREASE_MAP.get(level));
        dto.setCriticalHitRate(ADRENALINE_TO_CRITICAL_HIT_RATE_MAP.get(level));
    }
}

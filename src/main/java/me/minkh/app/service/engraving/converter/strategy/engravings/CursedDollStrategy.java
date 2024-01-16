package me.minkh.app.service.engraving.converter.strategy.engravings;

import me.minkh.app.dto.engraving.CombatAttributeDto;

import static me.minkh.app.service.LostArkConstants.CURSED_DOLL_TO_ATTACK_INCREASE_MAP;

public class CursedDollStrategy implements EngravingsConverterStrategy {

    @Override
    public void updateCombatAttributeDto(CombatAttributeDto dto, int level) {
        dto.setAttackIncrease(dto.getAttackIncrease() + CURSED_DOLL_TO_ATTACK_INCREASE_MAP.get(level));
    }
}

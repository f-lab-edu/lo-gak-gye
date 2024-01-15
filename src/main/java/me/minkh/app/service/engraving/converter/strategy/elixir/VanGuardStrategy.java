package me.minkh.app.service.engraving.converter.strategy.elixir;

import me.minkh.app.dto.engraving.CombatAttributeDto;

import static me.minkh.app.service.LostArkConstants.VANGUARD_ATTACK_INCREASE;

public class VanGuardStrategy implements ElixirConverterStrategy {

    @Override
    public boolean supports(int level) {
        return level == 35 || level == 40;
    }

    @Override
    public void updateCombatAttributeDto(CombatAttributeDto dto) {
        double currentAttackIncrease = dto.getAttackIncrease();
        dto.setAttackIncrease(currentAttackIncrease + VANGUARD_ATTACK_INCREASE);
    }
}

package me.minkh.app.service.engraving.converter.strategy.elixir;

import me.minkh.app.dto.engraving.CombatAttributeDto;

import static me.minkh.app.service.LostArkConstants.EXPERT_CRITICAL_HIT_RATE;

public class ExpertStrategy implements ElixirConverterStrategy {

    @Override
    public boolean supports(int level) {
        return level == 40;
    }

    @Override
    public void updateCombatAttributeDto(CombatAttributeDto dto) {
        dto.setCriticalHitRate(EXPERT_CRITICAL_HIT_RATE);
    }
}

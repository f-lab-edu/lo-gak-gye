package me.minkh.app.service.engraving.converter.strategy.elixir;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.springframework.stereotype.Component;

import static me.minkh.app.service.LostArkConstants.EXPERT;
import static me.minkh.app.service.LostArkConstants.EXPERT_CRITICAL_HIT_RATE;

@Component
public class ExpertStrategy implements ElixirConverterStrategy {

    @Override
    public boolean supports(String type, int level) {
        return type.equals(EXPERT) && level == 40;
    }

    @Override
    public void updateCombatAttributeDto(CombatAttributeDto dto) {
        dto.setCriticalHitRate(EXPERT_CRITICAL_HIT_RATE);
    }
}

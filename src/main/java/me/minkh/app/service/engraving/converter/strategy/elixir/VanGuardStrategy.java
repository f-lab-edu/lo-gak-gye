package me.minkh.app.service.engraving.converter.strategy.elixir;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.springframework.stereotype.Component;

import static me.minkh.app.service.LostArkConstants.VANGUARD;
import static me.minkh.app.service.LostArkConstants.VANGUARD_ATTACK_INCREASE;

@Component
public class VanGuardStrategy implements ElixirConverterStrategy {

    @Override
    public boolean supports(String type, int level) {
        return (type.equals(VANGUARD) && level == 35) ||
               (type.equals(VANGUARD) && level == 40);
    }

    @Override
    public void updateCombatAttributeDto(CombatAttributeDto dto) {
        double currentAttackIncrease = dto.getAttackIncrease();
        dto.setAttackIncrease(currentAttackIncrease + VANGUARD_ATTACK_INCREASE);
    }
}

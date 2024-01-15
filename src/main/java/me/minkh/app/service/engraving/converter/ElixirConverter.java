package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.request.Elixir;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.springframework.stereotype.Service;

import static me.minkh.app.service.LostArkConstants.*;

@Service
public class ElixirConverter {

    public CombatAttributeDto convert(Elixir elixir) {
        CombatAttributeDto stat = new CombatAttributeDto();

        int headOffensePower = elixir.getHeadOffensePower();
        stat.setAttackIncrease(ELIXIR_TO_ATTACK_INCREASE_MAP.get(headOffensePower));

        String type = elixir.getType();
        int level = elixir.getLevel();
        
        if (type.equals(VANGUARD)) {
            if (level == 35 || level == 40) {
                double currentAttackIncrease = stat.getAttackIncrease();
                double nextAttackIncrease = currentAttackIncrease + VANGUARD_ATTACK_INCREASE;
                stat.setAttackIncrease(nextAttackIncrease);
            }
        } else if (type.equals(EXPERT) && level == 40) {
            stat.setCriticalHitRate(7);
        }

        return stat;
    }

}

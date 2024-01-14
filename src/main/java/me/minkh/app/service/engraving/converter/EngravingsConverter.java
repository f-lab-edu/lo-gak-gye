package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.request.Engraving;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.minkh.app.service.LostArkConstants.*;

@Service
public class EngravingsConverter {

    public CombatAttributeDto convert(List<Engraving> engravings) {
        CombatAttributeDto stat = new CombatAttributeDto();
        for (Engraving engraving : engravings) {
            String name = engraving.getName();
            int level = engraving.getLevel();
            if (name.equals(CURSED_DOLL)) {
                stat.setAttackIncrease(stat.getAttackIncrease() + cursedDollToAttackIncreaseMap.get(level));
            } else if (name.equals(ADRENALINE)) {
                stat.setAttackIncrease(stat.getAttackIncrease() + adrenalineToAttackIncreaseMap.get(level));
                stat.setCriticalHitRate(adrenalineToCriticalHitRateMap.get(level));
            }
        }
        return stat;
    }
}
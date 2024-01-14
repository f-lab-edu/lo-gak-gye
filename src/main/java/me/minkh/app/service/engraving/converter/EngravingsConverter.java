package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.Engraving;
import me.minkh.app.dto.engraving.EngravingStat;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.minkh.app.service.LostArkConstants.*;

@Service
public class EngravingsConverter {

    public EngravingStat convert(List<Engraving> engravings) {
        EngravingStat stat = new EngravingStat();
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
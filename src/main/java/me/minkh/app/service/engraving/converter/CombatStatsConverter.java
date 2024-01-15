package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.dto.engraving.request.CombatStat;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.minkh.app.service.LostArkConstants.CRITICAL;
import static me.minkh.app.service.LostArkConstants.SWIFTNESS;

@Service
public class CombatStatsConverter {

    public CombatAttributeDto convert(List<CombatStat> combatStats) {
        CombatAttributeDto stat = new CombatAttributeDto();
        for (CombatStat combatStat : combatStats) {
            String type = combatStat.getType();
            int value = combatStat.getValue();

            if (type.equals(CRITICAL)) {
                double criticalHitRate = this.criticalHitRateCalculator(value);
                stat.setCriticalHitRate(criticalHitRate);
            } else if (type.equals(SWIFTNESS)) {
                double speedIncrease = this.speedIncreaseCalculator(value);
                stat.setSpeedIncrease(speedIncrease);
            }
        }
        return stat;
    }

    private double criticalHitRateCalculator(int critical) {
        return critical * 0.0357;
    }

    private double speedIncreaseCalculator(int swiftness) {
        return swiftness * 1.717 / 100;
    }
}

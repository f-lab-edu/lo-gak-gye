package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.EngravingStat;
import me.minkh.app.dto.engraving.ProfileStat;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.minkh.app.service.LostArkConstants.CRITICAL;
import static me.minkh.app.service.LostArkConstants.SWIFTNESS;

@Service
public class ProfileStatsConverter {

    public EngravingStat convert(List<ProfileStat> profileStats) {
        EngravingStat stat = new EngravingStat();
        for (ProfileStat profileStat : profileStats) {
            String type = profileStat.getType();
            int value = profileStat.getValue();

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

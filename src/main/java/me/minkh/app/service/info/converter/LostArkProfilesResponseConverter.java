package me.minkh.app.service.info.converter;

import me.minkh.app.dto.info.CombatStat;
import me.minkh.app.dto.lostark.LostArkProfilesResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static me.minkh.app.service.LostArkConstants.CRITICAL;
import static me.minkh.app.service.LostArkConstants.SWIFTNESS;

@Service
public class LostArkProfilesResponseConverter {

    public List<CombatStat> convert(LostArkProfilesResponse dto) {
        if (dto == null) {
            return new ArrayList<>();
        }

        List<CombatStat> dtos = new ArrayList<>();
        for (LostArkProfilesResponse.Stat stat : dto.getStats()) {
            if (isValid(stat)) {
                dtos.add(new CombatStat(stat.getType(), stat.getValue()));
            }
        }
        return dtos;
    }

    private boolean isValid(LostArkProfilesResponse.Stat stat) {
        return isCritical(stat) || isSwiftness(stat);
    }

    private boolean isCritical(LostArkProfilesResponse.Stat stat) {
        return stat.getType().equals(CRITICAL);
    }

    private boolean isSwiftness(LostArkProfilesResponse.Stat stat) {
        return stat.getType().equals(SWIFTNESS);
    }
}

package me.minkh.app.service.info.converter;

import me.minkh.app.dto.info.Engraving;
import me.minkh.app.dto.lostark.LostArkEngravingsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static me.minkh.app.service.LostArkConstants.ADRENALINE;
import static me.minkh.app.service.LostArkConstants.CURSED_DOLL;

@Service
public class LostArkEngravingsResponseConverter {

    public List<Engraving> convert(LostArkEngravingsResponse dto) {
        if (dto == null) {
            return new ArrayList<>();
        }

        List<Engraving> dtos = new ArrayList<>();
        List<String> engravings = getEngravings(dto);
        for (String engraving : engravings) {
            String[] array = engraving.split("Lv.");
            String name = array[0].trim();
            int level = Integer.parseInt(array[1].trim());
            if (isValid(name)) {
                dtos.add(new Engraving(name, level));
            }
        }
        return dtos;
    }

    private List<String> getEngravings(LostArkEngravingsResponse dto) {
        return dto.getEffects().stream()
                .map(LostArkEngravingsResponse.Effect::getName)
                .toList();
    }

    private boolean isValid(String name) {
        return isCursedDoll(name) || isAdrenaline(name);
    }

    private boolean isCursedDoll(String name) {
        return name.equals(CURSED_DOLL);
    }

    private boolean isAdrenaline(String name) {
        return name.equals(ADRENALINE);
    }
}

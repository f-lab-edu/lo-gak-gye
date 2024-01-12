package me.minkh.app.service;

import me.minkh.app.dto.lostark.CharacterEngraving;
import me.minkh.app.dto.lostark.EngravingsDto;
import org.springframework.stereotype.Service;

@Service
public class EngravingsDtoConverter {

    private static final String CURSED_DOLL  = "저주받은 인형";
    private static final String ADRENALINE = "아드레날린";

    public CharacterEngraving convert(EngravingsDto dto) {
        if (dto == null) {
            return new CharacterEngraving(0, 0);
        }

        String[] engravings = dto.getEffects().stream().map(EngravingsDto.Effect::getName).toArray(String[]::new);

        CharacterEngraving characterEngraving = new CharacterEngraving();
        for (String engraving : engravings) {
            String[] array = engraving.split("Lv.");
            String name = array[0].trim();
            int level = Integer.parseInt(array[1].trim());

            if (isValid(name)) {
                if (isCursedDoll(name)) {
                    characterEngraving.setCursedDoll(level);
                } else {
                    characterEngraving.setAdrenaline(level);
                }
            }
        }

        return characterEngraving;
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

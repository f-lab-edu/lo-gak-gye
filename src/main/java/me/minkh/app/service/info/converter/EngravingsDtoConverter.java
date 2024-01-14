package me.minkh.app.service.info.converter;

import me.minkh.app.dto.info.EngravingResponseDto;
import me.minkh.app.dto.lostark.EngravingsDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static me.minkh.app.service.LostArkConstants.ADRENALINE;
import static me.minkh.app.service.LostArkConstants.CURSED_DOLL;

@Service
public class EngravingsDtoConverter {

    public List<EngravingResponseDto> convert(EngravingsDto dto) {
        if (dto == null) {
            return new ArrayList<>();
        }

        List<EngravingResponseDto> dtos = new ArrayList<>();
        List<String> engravings = getEngravings(dto);
        for (String engraving : engravings) {
            String[] array = engraving.split("Lv.");
            String name = array[0].trim();
            int level = Integer.parseInt(array[1].trim());
            if (isValid(name)) {
                dtos.add(new EngravingResponseDto(name, level));
            }
        }
        return dtos;
    }

    private List<String> getEngravings(EngravingsDto dto) {
        return dto.getEffects().stream()
                .map(EngravingsDto.Effect::getName)
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
package me.minkh.app.service;

import me.minkh.app.dto.lostark.CharacterStat;
import me.minkh.app.dto.lostark.ProfileDto;
import org.springframework.stereotype.Service;

@Service
public class ProfileDtoConverter {

    public CharacterStat convert(ProfileDto dto) {
        if (dto == null) {
            return new CharacterStat(0, 0);
        }

        CharacterStat characterStat = new CharacterStat();
        for (ProfileDto.Stat stat : dto.getStats()) {
            if (isValid(stat)) {
                if (isCritical(stat)) {
                    characterStat.setCritical(stat.getValue());
                } else {
                    characterStat.setSwiftness(stat.getValue());
                }
            }
        }
        return characterStat;
    }

    private boolean isValid(ProfileDto.Stat stat) {
        return isCritical(stat) || isSwiftness(stat);
    }

    private boolean isCritical(ProfileDto.Stat stat) {
        return stat.getType().equals("치명");
    }

    private boolean isSwiftness(ProfileDto.Stat stat) {
        return stat.getType().equals("신속");
    }
}

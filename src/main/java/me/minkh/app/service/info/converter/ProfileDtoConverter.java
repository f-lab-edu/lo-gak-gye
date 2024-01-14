package me.minkh.app.service.info.converter;

import me.minkh.app.dto.info.ProfileStatResponseDto;
import me.minkh.app.dto.lostark.ProfileDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static me.minkh.app.service.LostArkConstants.CRITICAL;
import static me.minkh.app.service.LostArkConstants.SWIFTNESS;

@Service
public class ProfileDtoConverter {

    public List<ProfileStatResponseDto> convert(ProfileDto dto) {
        if (dto == null) {
            return new ArrayList<>();
        }

        List<ProfileStatResponseDto> dtos = new ArrayList<>();
        for (ProfileDto.Stat stat : dto.getStats()) {
            if (isValid(stat)) {
                dtos.add(new ProfileStatResponseDto(stat.getType(), stat.getValue()));
            }
        }
        return dtos;
    }

    private boolean isValid(ProfileDto.Stat stat) {
        return isCritical(stat) || isSwiftness(stat);
    }

    private boolean isCritical(ProfileDto.Stat stat) {
        return stat.getType().equals(CRITICAL);
    }

    private boolean isSwiftness(ProfileDto.Stat stat) {
        return stat.getType().equals(SWIFTNESS);
    }
}

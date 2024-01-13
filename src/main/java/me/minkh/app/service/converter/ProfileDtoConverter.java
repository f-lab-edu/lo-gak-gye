package me.minkh.app.service.converter;

import me.minkh.app.dto.lostark.ProfileDto;
import me.minkh.app.dto.info.ProfileStatResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return stat.getType().equals("치명");
    }

    private boolean isSwiftness(ProfileDto.Stat stat) {
        return stat.getType().equals("신속");
    }
}

package me.minkh.app.service;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.info.InfoResponseDto;
import me.minkh.app.dto.lostark.CharacterEngraving;
import me.minkh.app.dto.lostark.CharacterStat;
import me.minkh.app.dto.lostark.EngravingsDto;
import me.minkh.app.dto.lostark.ProfileDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final LostArkApiService lostArkApiService;
    private final ProfileDtoConverter profileDtoConverter;
    private final EngravingsDtoConverter engravingsDtoConverter;

    public InfoResponseDto info(String characterName) {
        ProfileDto profileDto = this.lostArkApiService.getProfiles(characterName);
        CharacterStat characterStat = this.profileDtoConverter.convert(profileDto);

        EngravingsDto engravings = this.lostArkApiService.getEngravings(characterName);
        CharacterEngraving characterEngraving = this.engravingsDtoConverter.convert(engravings);

        return null;
    }

}

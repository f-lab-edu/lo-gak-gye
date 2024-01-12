package me.minkh.app.service;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.info.InfoResponseDto;
import me.minkh.app.dto.lostark.CharacterStat;
import me.minkh.app.dto.lostark.ProfileDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final LostArkApiService lostArkApiService;
    private final ProfileDtoConverter profileDtoConverter;

    public InfoResponseDto info(String characterName) {
        ProfileDto profileDto = this.lostArkApiService.getProfiles(characterName);
        CharacterStat characterStat = this.profileDtoConverter.convert(profileDto);

        System.out.println(characterStat);

        return null;
    }

}

package me.minkh.app.service;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.info.InfoResponseDto;
import me.minkh.app.dto.lostark.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final LostArkApiService lostArkApiService;
    private final EquipmentDtoConverter equipmentDtoConverter;
    private final ProfileDtoConverter profileDtoConverter;
    private final EngravingsDtoConverter engravingsDtoConverter;

    public InfoResponseDto info(String characterName) {
        EquipmentDto[] equipment = this.lostArkApiService.getEquipment(characterName);
        CharacterEquipment characterEquipment = this.equipmentDtoConverter.convert(equipment);

        ProfileDto profileDto = this.lostArkApiService.getProfiles(characterName);
        CharacterStat characterStat = this.profileDtoConverter.convert(profileDto);

        EngravingsDto engravings = this.lostArkApiService.getEngravings(characterName);
        CharacterEngraving characterEngraving = this.engravingsDtoConverter.convert(engravings);

        return new InfoResponseDto(
                characterEquipment,
                characterStat,
                characterEngraving);
    }

}

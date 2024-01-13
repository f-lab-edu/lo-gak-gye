package me.minkh.app.service.info;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.info.EngravingResponseDto;
import me.minkh.app.dto.info.EquipmentResponseDto;
import me.minkh.app.dto.info.InfoResponseDto;
import me.minkh.app.dto.info.ProfileStatResponseDto;
import me.minkh.app.dto.lostark.EngravingsDto;
import me.minkh.app.dto.lostark.EquipmentDto;
import me.minkh.app.dto.lostark.ProfileDto;
import me.minkh.app.service.LostArkApiService;
import me.minkh.app.service.info.converter.EngravingsDtoConverter;
import me.minkh.app.service.info.converter.EquipmentDtoConverter;
import me.minkh.app.service.info.converter.ProfileDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InfoService {

    private final LostArkApiService lostArkApiService;
    private final EquipmentDtoConverter equipmentDtoConverter;
    private final ProfileDtoConverter profileDtoConverter;
    private final EngravingsDtoConverter engravingsDtoConverter;

    public InfoResponseDto info(String characterName) {
        EquipmentDto[] equipment = this.lostArkApiService.getEquipment(characterName);
        EquipmentResponseDto characterEquipment = this.equipmentDtoConverter.convert(equipment);

        ProfileDto profileDto = this.lostArkApiService.getProfiles(characterName);
        List<ProfileStatResponseDto> profileStatResponseDtos = this.profileDtoConverter.convert(profileDto);

        EngravingsDto engravings = this.lostArkApiService.getEngravings(characterName);
        List<EngravingResponseDto> engravingResponseDtos = this.engravingsDtoConverter.convert(engravings);

        return new InfoResponseDto(
                characterEquipment,
                profileStatResponseDtos,
                engravingResponseDtos);
    }
}
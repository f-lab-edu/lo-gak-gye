package me.minkh.app.service.info;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.info.Engraving;
import me.minkh.app.dto.info.InfoResponse;
import me.minkh.app.dto.info.CombatStat;
import me.minkh.app.dto.lostark.LostArkEngravingsResponse;
import me.minkh.app.dto.lostark.LostArkEquipmentResponse;
import me.minkh.app.dto.lostark.LostArkProfilesResponse;
import me.minkh.app.service.LostArkApiService;
import me.minkh.app.service.info.converter.LostArkEngravingsResponseConverter;
import me.minkh.app.service.info.converter.LostArkEquipmentResponseConverter;
import me.minkh.app.service.info.converter.LostArkProfilesResponseConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InfoService {

    private final LostArkApiService lostArkApiService;
    private final LostArkEquipmentResponseConverter lostArkEquipmentResponseConverter;
    private final LostArkProfilesResponseConverter lostArkProfilesResponseConverter;
    private final LostArkEngravingsResponseConverter lostArkEngravingsResponseConverter;

    public InfoResponse info(String characterName) {
        LostArkEquipmentResponse[] equipment = this.lostArkApiService.getEquipment(characterName);
        String artifact = this.lostArkEquipmentResponseConverter.convert(equipment);

        LostArkProfilesResponse lostArkProfilesResponse = this.lostArkApiService.getProfiles(characterName);
        List<CombatStat> combatStats = this.lostArkProfilesResponseConverter.convert(lostArkProfilesResponse);

        LostArkEngravingsResponse engravings = this.lostArkApiService.getEngravings(characterName);
        List<Engraving> engravingResponseDtos = this.lostArkEngravingsResponseConverter.convert(engravings);

        return new InfoResponse(
                artifact,
                combatStats,
                engravingResponseDtos);
    }
}
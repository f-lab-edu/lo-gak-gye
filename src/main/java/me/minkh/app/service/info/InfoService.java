package me.minkh.app.service.info;

import lombok.RequiredArgsConstructor;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.dto.info.CombatStat;
import me.minkh.app.dto.info.Engraving;
import me.minkh.app.dto.info.InfoResponse;
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
    private final AccountRepository accountRepository;

    public InfoResponse info(String characterName, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException(accountId + "은 올바르지 않은 요청입니다."));

        String apiKey = account.getApiKey();
        if (apiKey == null) {
            throw new IllegalArgumentException("API키 업데이트가 필요합니다.");
        }

        LostArkEquipmentResponse[] equipment = this.lostArkApiService.getEquipment(characterName, apiKey);
        String artifact = this.lostArkEquipmentResponseConverter.convert(equipment);

        LostArkProfilesResponse lostArkProfilesResponse = this.lostArkApiService.getProfiles(characterName, apiKey);
        List<CombatStat> combatStats = this.lostArkProfilesResponseConverter.convert(lostArkProfilesResponse);

        LostArkEngravingsResponse engravings = this.lostArkApiService.getEngravings(characterName, apiKey);
        List<Engraving> engravingResponseDtos = this.lostArkEngravingsResponseConverter.convert(engravings);

        return new InfoResponse(
                artifact,
                combatStats,
                engravingResponseDtos);
    }
}
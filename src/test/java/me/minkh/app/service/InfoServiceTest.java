package me.minkh.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.dto.info.InfoResponse;
import me.minkh.app.dto.lostark.LostArkEngravingsResponse;
import me.minkh.app.dto.lostark.LostArkEquipmentResponse;
import me.minkh.app.dto.lostark.LostArkProfilesResponse;
import me.minkh.app.service.info.InfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class InfoServiceTest {

    String path = "src/test/java/me/minkh/app/";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    InfoService homeService;

    @Autowired
    AccountRepository accountRepository;

    @MockBean
    LostArkApiService lostArkApiService;

    @DisplayName("info 단위테스트")
    @Test
    void getInfo() throws IOException {
        // given
        String characterName = "성공하는_아이디";
        LostArkEquipmentResponse[] lostArkEquipmentResponse = getEquipmentDto();
        LostArkEngravingsResponse lostArkEngravingsResponse = getEngravingsDto();
        LostArkProfilesResponse lostArkProfilesResponse = getProfileDto();
        Account savedAccount = accountRepository.save(
                Account.builder()
                        .email("test@test.com")
                        .name("test")
                        .apiKey(UUID.randomUUID().toString())
                        .build()
        );

        // when
        when(lostArkApiService.getEquipment(characterName, savedAccount.getApiKey())).thenReturn(lostArkEquipmentResponse);
        when(lostArkApiService.getEngravings(characterName, savedAccount.getApiKey())).thenReturn(lostArkEngravingsResponse);
        when(lostArkApiService.getProfiles(characterName, savedAccount.getApiKey())).thenReturn(lostArkProfilesResponse);
        InfoResponse result = homeService.info(characterName, savedAccount.getId());

        // then
        assertThat(result.getArtifact()).isEqualTo("사멸");
        assertThat(result.getCombatStats().size()).isEqualTo(2);
        assertThat(result.getEngravings().size()).isEqualTo(2);
    }

    private LostArkEquipmentResponse[] getEquipmentDto() throws IOException {
        String body = new String(Files.readAllBytes(Paths.get(path + "equipment.json")));
        return this.objectMapper.readValue(body, LostArkEquipmentResponse[].class);
    }

    private LostArkProfilesResponse getProfileDto() throws IOException {
        String body = new String(Files.readAllBytes(Paths.get(path + "profile.json")));
        return this.objectMapper.readValue(body, LostArkProfilesResponse.class);
    }

    private LostArkEngravingsResponse getEngravingsDto() throws IOException {
        String body = new String(Files.readAllBytes(Paths.get(path + "engravings.json")));
        return this.objectMapper.readValue(body, LostArkEngravingsResponse.class);
    }
}
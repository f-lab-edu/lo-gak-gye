package me.minkh.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.minkh.app.dto.info.InfoResponseDto;
import me.minkh.app.dto.lostark.EngravingsDto;
import me.minkh.app.dto.lostark.EquipmentDto;
import me.minkh.app.dto.lostark.ProfileDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class HomeServiceTest {

    String path = "src/test/java/me/minkh/app/";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    HomeService homeService;

    @MockBean
    LostArkApiService lostArkApiService;

    @DisplayName("info 단위테스트")
    @Test
    void getInfo() throws IOException {
        // given
        String characterName = "성공하는_아이디";
        EquipmentDto[] equipmentDto = getEquipmentDto();
        EngravingsDto engravingsDto = getEngravingsDto();
        ProfileDto profileDto = getProfileDto();

        // when
        when(lostArkApiService.getEquipment(characterName)).thenReturn(equipmentDto);
        when(lostArkApiService.getEngravings(characterName)).thenReturn(engravingsDto);
        when(lostArkApiService.getProfiles(characterName)).thenReturn(profileDto);
        InfoResponseDto result = homeService.info(characterName);

        // then
        assertThat(result.getArtifact()).isEqualTo("사멸");
        assertThat(result.getProfileStats().size()).isEqualTo(2);
        assertThat(result.getEngravings().size()).isEqualTo(2);
    }

    private EquipmentDto[] getEquipmentDto() throws IOException {
        String body = new String(Files.readAllBytes(Paths.get(path + "equipment.json")));
        return this.objectMapper.readValue(body, EquipmentDto[].class);
    }

    private ProfileDto getProfileDto() throws IOException {
        String body = new String(Files.readAllBytes(Paths.get(path + "profile.json")));
        return this.objectMapper.readValue(body, ProfileDto.class);
    }

    private EngravingsDto getEngravingsDto() throws IOException {
        String body = new String(Files.readAllBytes(Paths.get(path + "engravings.json")));
        return this.objectMapper.readValue(body, EngravingsDto.class);
    }
}
package me.minkh.app.service;

import me.minkh.app.dto.lostark.LostArkEngravingsResponse;
import me.minkh.app.dto.lostark.LostArkEquipmentResponse;
import me.minkh.app.dto.lostark.LostArkProfilesResponse;
import me.minkh.app.exception.CharacterNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class LostArkApiServiceMockTest {

    @Autowired
    LostArkApiService lostArkApiService;

    @MockBean
    RestTemplate restTemplate;

    String path = "src/test/java/me/minkh/app/";

    @DisplayName("프로필 조회에 성공하는 테스트")
    @Test
    void getProfiles() throws IOException {
        // given
        String characterName = "성공하는_아이디";
        String body = new String(Files.readAllBytes(Paths.get(path + "profile.json")));

        // when
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class))).thenReturn(ResponseEntity.ok(body));
        LostArkProfilesResponse lostArkProfilesResponse = this.lostArkApiService.getProfiles(characterName);

        // then
        assertThat(lostArkProfilesResponse.getStats().size()).isEqualTo(8);
        assertThat(lostArkProfilesResponse.getServerName()).isEqualTo("루페온");
    }

    @DisplayName("각인 조회에 성공하는 테스트")
    @Test
    void getEngravings() throws IOException {
        // given
        String characterName = "성공하는_아이디";
        String body = new String(Files.readAllBytes(Paths.get(path + "engravings.json")));

        // when
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class))).thenReturn(ResponseEntity.ok(body));
        LostArkEngravingsResponse engravings = this.lostArkApiService.getEngravings(characterName);

        // then
        assertThat(engravings.getEffects().size()).isEqualTo(6);
    }

    @DisplayName("장비 조회에 성공하는 테스트")
    @Test
    void getEquipment() throws IOException {
        // given
        String characterName = "성공하는_아이디";
        String body = new String(Files.readAllBytes(Paths.get(path + "equipment.json")));

        // when
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class))).thenReturn(ResponseEntity.ok(body));
        LostArkEquipmentResponse[] lostArkEquipmentResponses = this.lostArkApiService.getEquipment(characterName);

        // then
        assertThat(lostArkEquipmentResponses.length).isEqualTo(16);
        assertThat(lostArkEquipmentResponses[0].getGrade()).isEqualTo("에스더");
    }

    @DisplayName("존재하지 않은 아이디 조회시 실패하는 테스트")
    @Test
    void getProfilesFail() {
        // given
        String characterName = "실패하는_없는_아이디";

        // when & then
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class))).thenReturn(ResponseEntity.ok("null"));

        assertThatThrownBy(() -> this.lostArkApiService.getProfiles(characterName))
                .isInstanceOf(CharacterNotFoundException.class)
                .hasMessage(characterName + "에 해당하는 캐릭터가 없습니다.");
    }

    @DisplayName("인증 토큰이 올바르지 않을 때, 실패하는 테스트")
    @Test
    void getProfiles401() {
        // given
        String characterName = "성공하는_아이디";

        // when & then
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class))).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        assertThatThrownBy(() -> this.lostArkApiService.getProfiles(characterName))
                .isInstanceOf(HttpClientErrorException.class);
    }
}
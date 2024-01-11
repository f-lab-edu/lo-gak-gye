package me.minkh.app.service;

import me.minkh.app.dto.lostark.ProfileDto;
import me.minkh.app.exception.CharacterNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class LostArkApiServiceTest {

    @Autowired
    LostArkApiService lostArkApiService;

    @DisplayName("프로필 조회에 성공하는 테스트")
    @Test
    void getProfiles() {
        // given
        ProfileDto profileDto = this.lostArkApiService.getProfiles("짱돌봇");

        // when
        int length = profileDto.getStats().toArray().length;

        // then
        assertThat(length).isEqualTo(8);
    }

    @DisplayName("프로필 조회에 실패하는 테스트")
    @Test
    void getProfilesFail() {
        // given
        String characterName = "XX331232";

        // when & then
        assertThatThrownBy(() -> this.lostArkApiService.getProfiles(characterName))
                .isInstanceOf(CharacterNotFoundException.class)
                .hasMessage(characterName + "에 해당하는 캐릭터가 없습니다.");
    }
}
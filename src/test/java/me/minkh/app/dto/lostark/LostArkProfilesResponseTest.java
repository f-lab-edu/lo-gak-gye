package me.minkh.app.dto.lostark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LostArkProfilesResponseTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("ProfileDTO 변환 성공 테스트")
    @Test
    void profileDtoConvertSuccessTest() throws IOException {
        // given
        String profileJson = new String(Files.readAllBytes(Paths.get("src/test/java/me/minkh/app/profile.json")));
        LostArkProfilesResponse lostArkProfilesResponse = objectMapper.readValue(profileJson, LostArkProfilesResponse.class);

        // when
        int length = lostArkProfilesResponse.getStats().toArray().length;

        // then
        assertThat(length).isEqualTo(8);
    }

    @DisplayName("ProfileDTO 변환 실패 테스트")
    @Test
    void profileDtoConvertFailTest() {
        // given
        String errorJson = """
            {
              "error": "json",
            }
            """;

        // when & then
        assertThatThrownBy(() -> objectMapper.readValue(errorJson, LostArkProfilesResponse.class))
                .isInstanceOf(JsonProcessingException.class);
    }
}
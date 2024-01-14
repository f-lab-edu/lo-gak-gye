package me.minkh.app.service.engraving.converter;


import me.minkh.app.dto.engraving.EngravingStat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ArtifactConverterTest {

    ArtifactConverter artifactConverter = new ArtifactConverter();

    @DisplayName("악몽, 구원, 지배")
    @ParameterizedTest
    @ValueSource(strings = { "악몽", "구원", "지배" })
    void testSuccess1(String artifact) {
        // when
        EngravingStat result = artifactConverter.convert(artifact);

        // then
        assertThat(result).isEqualTo(new EngravingStat(0, 0, 0, 0));
    }

    @DisplayName("사멸")
    @Test
    void testSuccess2() {
        // given
        String artifact = "사멸";

        // when
        EngravingStat result = artifactConverter.convert(artifact);

        // then
        assertThat(result).isEqualTo(new EngravingStat(22, 65, 0, 0));
    }

    @DisplayName("환각")
    @Test
    void testSuccess3() {
        // given
        String artifact = "환각";

        // when
        EngravingStat result = artifactConverter.convert(artifact);

        // then
        assertThat(result).isEqualTo(new EngravingStat(28, 0, 0, 0));
    }

    @DisplayName("이상한 값")
    @Test
    void testFail() {
        // given
        String artifact = "이상한_값";

        // when & then
        Assertions.assertThatThrownBy(() -> artifactConverter.convert(artifact))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
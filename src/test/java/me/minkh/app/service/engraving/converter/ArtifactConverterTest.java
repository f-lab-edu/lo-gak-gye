package me.minkh.app.service.engraving.converter;


import me.minkh.app.domain.engraving.Artifact;
import me.minkh.app.domain.engraving.ArtifactRepository;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static me.minkh.app.service.LostArkConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ArtifactConverterTest {

    @Autowired
    ArtifactConverter artifactConverter;

    @Autowired
    ArtifactRepository artifactRepository;

    @BeforeEach
    void beforeEach() {
        this.artifactRepository.save(new Artifact(NIGHTMARE, 0, 0, 0, 0));
        this.artifactRepository.save(new Artifact(SALVATION, 0, 0, 0, 0));
        this.artifactRepository.save(new Artifact(DOMINION, 0, 0, 0, 0));
        this.artifactRepository.save(new Artifact(ENTROPY, 22, 65, 0, 0));
        this.artifactRepository.save(new Artifact(HALLUCINATION, 28, 0, 0, 0));
    }

    @AfterEach
    void afterEach() {
        this.artifactRepository.deleteAll();
    }

    @DisplayName("악몽, 구원, 지배")
    @ParameterizedTest
    @ValueSource(strings = { "악몽", "구원", "지배" })
    void testSuccess1(String artifact) {
        // when
        CombatAttributeDto result = artifactConverter.convert(artifact);

        // then
        assertThat(result).isEqualTo(new CombatAttributeDto(0, 0, 0, 0));
    }

    @DisplayName("사멸")
    @Test
    void testSuccess2() {
        // given
        String artifact = "사멸";

        // when
        CombatAttributeDto result = artifactConverter.convert(artifact);

        // then
        assertThat(result).isEqualTo(new CombatAttributeDto(22, 65, 0, 0));
    }

    @DisplayName("환각")
    @Test
    void testSuccess3() {
        // given
        String artifact = "환각";

        // when
        CombatAttributeDto result = artifactConverter.convert(artifact);

        // then
        assertThat(result).isEqualTo(new CombatAttributeDto(28, 0, 0, 0));
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
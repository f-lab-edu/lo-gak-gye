package me.minkh.app.service.engraving;

import me.minkh.app.domain.engraving.preset.PresetRepository;
import me.minkh.app.dto.engraving.request.*;
import me.minkh.app.dto.engraving.response.EngravingPresetResponse;
import me.minkh.app.service.LostArkConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EngravingServiceTest {

    @Autowired
    PresetRepository presetRepository;

    @Autowired
    EngravingService engravingService;

    @AfterEach
    void afterEach() {
        presetRepository.deleteAll();
    }

    @DisplayName("프리셋 저장에 성공하는 테스트")
    @Test
    void savePreset() {
        // given
        String artifact = LostArkConstants.NIGHTMARE;
        ElixirDto elixirDto = new ElixirDto(LostArkConstants.EXPERT, 35, 5);
        EtcDto etcDto = new EtcDto(30.0, 2.1, 11.3, 5.4);
        List<CombatStatDto> combatStatDtos = List.of(
                new CombatStatDto(LostArkConstants.CRITICAL, 650),
                new CombatStatDto(LostArkConstants.SWIFTNESS, 1650));
        List<EngravingDto> engravingDtos = List.of(new EngravingDto(LostArkConstants.CURSED_DOLL, 3),
                new EngravingDto(LostArkConstants.ADRENALINE, 3));

        EngravingSetupRequest request = EngravingSetupRequest.builder()
                .artifact(artifact)
                .elixir(elixirDto)
                .etc(etcDto)
                .combatStats(combatStatDtos)
                .engravings(engravingDtos)
                .build();

        // when
        EngravingPresetResponse response = this.engravingService.savePreset(request);

        // then
        assertThat(response.getArtifact()).isEqualTo(artifact);
        assertThat(response.getElixir().getType()).isEqualTo(LostArkConstants.EXPERT);
        assertThat(response.getEtc().getAttackIncrease()).isEqualTo(11.3);
        assertThat(response.getCombatStats().size()).isEqualTo(2);
        assertThat(response.getEngravings().size()).isEqualTo(2);
    }
}
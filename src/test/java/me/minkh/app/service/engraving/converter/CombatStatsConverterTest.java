package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.dto.engraving.request.CombatStatDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class CombatStatsConverterTest {

    @Autowired
    CombatStatsConverter combatStatsConverter;

    @DisplayName("치명이 1,000 일 때는 치명타 적중률이 35.7이다.")
    @Test
    void test() {
        // given
        List<CombatStatDto> combatStats = List.of(new CombatStatDto("치명", 1_000));

        // when
        CombatAttributeDto combatAttributeDto = combatStatsConverter.convert(combatStats);

        // then
        assertThat(combatAttributeDto.getCriticalHitRate()).isEqualTo(35.7);
    }

    @DisplayName("신속이 1,000 일 때는 이동속도 증가량이 17.17이다.")
    @Test
    void test2() {
        // given
        List<CombatStatDto> combatStats = List.of(new CombatStatDto("신속", 1_000));

        // when
        CombatAttributeDto combatAttributeDto = combatStatsConverter.convert(combatStats);

        // then
        assertThat(combatAttributeDto.getSpeedIncrease()).isEqualTo(17.17);
    }

    @DisplayName("치명, 신속이 1,000 일 때는 치명타 적중률이 35.7, 이동속도 증가량이 17.17이다.")
    @Test
    void test3() {
        // given
        List<CombatStatDto> combatStats = List.of(
                new CombatStatDto("치명", 1_000),
                new CombatStatDto("신속", 1_000));

        // when
        CombatAttributeDto combatAttributeDto = combatStatsConverter.convert(combatStats);

        // then
        assertThat(combatAttributeDto).isEqualTo(new CombatAttributeDto(35.7, 0, 0, 17.17));
    }

    @DisplayName("치명, 신속이 외에 다른 값이 들어오면 무시한다.")
    @Test
    void test4() {
        // given
        List<CombatStatDto> combatStats = List.of(
                new CombatStatDto("이상한 값1", 1_000),
                new CombatStatDto("이상한 값2", 1_000));

        // when
        CombatAttributeDto combatAttributeDto = combatStatsConverter.convert(combatStats);

        // then
        assertThat(combatAttributeDto).isEqualTo(new CombatAttributeDto(0, 0, 0, 0));
    }

}
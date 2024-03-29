package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.request.ElixirDto;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ElixirConverterTest {

    @Autowired
    ElixirConverter elixirConverter;

    @DisplayName("머리 공격력 증가량 테스트")
    @Test
    void head() {
        extracted(0,0.0);
        extracted(1,0.23);
        extracted(2,0.47);
        extracted(3,0.72);
        extracted(4,1.08);
        extracted(5,1.44);
    }

    void extracted(int headOffensePower, double attackIncrease) {
        // given
        ElixirDto elixir = new ElixirDto("회심", 0, headOffensePower);

        // when
        CombatAttributeDto combatAttributeDto = elixirConverter.convert(elixir);

        // then
        assertThat(combatAttributeDto.getAttackIncrease()).isEqualTo(attackIncrease);
    }

    @DisplayName("선봉대 Lv35 일 때, 머리 + 공격력 3이 누적됨")
    @Test
    void vanguard35() {
        // given
        ElixirDto elixir = new ElixirDto("선봉대", 35, 3);

        // when
        CombatAttributeDto combatAttributeDto = elixirConverter.convert(elixir);

        // then
        assertThat(combatAttributeDto.getAttackIncrease()).isEqualTo(0.72 + 3.0);
    }

    @DisplayName("선봉대 Lv40 일 때, 머리 + 공격력 3이 누적됨")
    @Test
    void vanguard40() {
        // given
        ElixirDto elixir = new ElixirDto("선봉대", 40, 5);

        // when
        CombatAttributeDto combatAttributeDto = elixirConverter.convert(elixir);

        // then
        assertThat(combatAttributeDto.getAttackIncrease()).isEqualTo(1.44 + 3.0);
    }

    @DisplayName("달인 Lv35 일 때, 치명타 적즁률이 올라가지 않음")
    @Test
    void expert35() {
        // given
        ElixirDto elixir = new ElixirDto("달인", 35, 3);

        // when
        CombatAttributeDto combatAttributeDto = elixirConverter.convert(elixir);

        // then
        assertThat(combatAttributeDto.getAttackIncrease()).isEqualTo(0.72);
        assertThat(combatAttributeDto.getCriticalHitRate()).isZero();
    }

    @DisplayName("달인 Lv40 일 때, 치명타 적즁률이 7 올라감")
    @Test
    void expert40() {
        // given
        ElixirDto elixir = new ElixirDto("달인", 40, 3);

        // when
        CombatAttributeDto combatAttributeDto = elixirConverter.convert(elixir);

        // then
        assertThat(combatAttributeDto.getAttackIncrease()).isEqualTo(0.72);
        assertThat(combatAttributeDto.getCriticalHitRate()).isEqualTo(7);
    }
}
package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.Elixir;
import me.minkh.app.dto.engraving.EngravingStat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ElixirConverterTest {

    ElixirConverter elixirConverter = new ElixirConverter();

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
        Elixir elixir = new Elixir("회심", 0, headOffensePower);

        // when
        EngravingStat engravingStat = elixirConverter.convert(elixir);

        // then
        assertThat(engravingStat.getAttackIncrease()).isEqualTo(attackIncrease);
    }

    @DisplayName("선봉대 Lv35 일 때, 머리 + 공격력 3이 누적됨")
    @Test
    void vanguard35() {
        // given
        Elixir elixir = new Elixir("선봉대", 35, 3);

        // when
        EngravingStat engravingStat = elixirConverter.convert(elixir);

        // then
        assertThat(engravingStat.getAttackIncrease()).isEqualTo(0.72 + 3.0);
    }

    @DisplayName("선봉대 Lv40 일 때, 머리 + 공격력 3이 누적됨")
    @Test
    void vanguard40() {
        // given
        Elixir elixir = new Elixir("선봉대", 40, 5);

        // when
        EngravingStat engravingStat = elixirConverter.convert(elixir);

        // then
        assertThat(engravingStat.getAttackIncrease()).isEqualTo(1.44 + 3.0);
    }

    @DisplayName("달인 Lv35 일 때, 치명타 적즁률이 올라가지 않음")
    @Test
    void expert35() {
        // given
        Elixir elixir = new Elixir("달인", 35, 3);

        // when
        EngravingStat engravingStat = elixirConverter.convert(elixir);

        // then
        assertThat(engravingStat.getAttackIncrease()).isEqualTo(0.72);
        assertThat(engravingStat.getCriticalHitRate()).isEqualTo(0);
    }

    @DisplayName("달인 Lv40 일 때, 치명타 적즁률이 7 올라감")
    @Test
    void expert40() {
        // given
        Elixir elixir = new Elixir("달인", 40, 3);

        // when
        EngravingStat engravingStat = elixirConverter.convert(elixir);

        // then
        assertThat(engravingStat.getAttackIncrease()).isEqualTo(0.72);
        assertThat(engravingStat.getCriticalHitRate()).isEqualTo(7);
    }
}
package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.request.Engraving;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EngravingsConverterTest {

    private static final String CURSED_DOLL = "저주받은 인형";
    private static final String ADRENALINE = "아드레날린";

    EngravingsConverter engravingsConverter = new EngravingsConverter();

    @ParameterizedTest(name = "저주받은 인형 : {0}, 아드레날린 : {1}, 공격력 증가 : {2}")
    @MethodSource("method")
    @DisplayName("저주받은 인형 0, 아드레날린 0 = 0.0")
    void test(int cursedDollLevel, int adrenalineLevel, double expectedAttackIncrease) {
        // given
        List<Engraving> engravings = getEngravings(cursedDollLevel, adrenalineLevel);

        // when
        CombatAttributeDto combatAttributeDto = engravingsConverter.convert(engravings);

        // then
        assertThat(combatAttributeDto.getAttackIncrease()).isEqualTo(expectedAttackIncrease);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("아드레날린 레벨에 따른 치명타 적중률")
    void test2(int level) {
        // given
        List<Engraving> engravings = getEngravings(0, level);

        // when
        CombatAttributeDto combatAttributeDto = engravingsConverter.convert(engravings);

        // then
        assertThat(combatAttributeDto.getCriticalHitRate()).isEqualTo(level * 5);
    }

    private static Stream<Arguments> method() {
        return Stream.of(
                // 저주받은 인형 0, 아드레날린 0 ~ 3
                Arguments.of(0, 0, 0.0),
                Arguments.of(0, 1, 1.8),
                Arguments.of(0, 2, 3.6),
                Arguments.of(0, 3, 6.0),
                // 저주받은 인형 1, 아드레날린 0 ~ 3
                Arguments.of(1, 0, 3 + 0.0),
                Arguments.of(1, 1, 3 + 1.8),
                Arguments.of(1, 2, 3 + 3.6),
                Arguments.of(1, 3, 3 + 6.0),
                // 저주받은 인형 2, 아드레날린 0 ~ 3
                Arguments.of(2, 0, 8 + 0.0),
                Arguments.of(2, 1, 8 + 1.8),
                Arguments.of(2, 2, 8 + 3.6),
                Arguments.of(2, 3, 8 + 6.0),
                // 저주받은 인형 3, 아드레날린 0 ~ 3
                Arguments.of(3, 0, 16 + 0.0),
                Arguments.of(3, 1, 16 + 1.8),
                Arguments.of(3, 2, 16 + 3.6),
                Arguments.of(3, 3, 16 + 6.0)
        );
    }

    private List<Engraving> getEngravings(int adrenalineLevel, int expectedAttackIncrease) {
        return List.of(new Engraving(CURSED_DOLL, adrenalineLevel), new Engraving(ADRENALINE, expectedAttackIncrease));
    }
}
package me.minkh.app.domain.engraving;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class CombatAttributeTest {

    int nThreads = 100;
    int nDtos = 5;
    double value = 10;

    @DisplayName("동시성 이슈가 있는지 확인하는 테스트")
    @Test
    void test() {
        ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);

        List<CombatAttributeDto> dtos = createDtos();

        CombatAttribute combatAttribute = new CombatAttribute();
        for (int i = 0; i < nThreads; i++) {
            threadPool.execute(() -> combatAttribute.accumulate(dtos));
        }

        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) break;
        }

        double expected = nThreads * nDtos * value;
        assertThat(combatAttribute.getAttackIncrease().get()).isEqualTo(expected);
        assertThat(combatAttribute.getCriticalDamage().get()).isEqualTo(expected);
        assertThat(combatAttribute.getSpeedIncrease().get()).isEqualTo(expected);
        assertThat(combatAttribute.getCriticalHitRate().get()).isEqualTo(expected);
    }

    private List<CombatAttributeDto> createDtos() {
        List<CombatAttributeDto> list = new ArrayList<>();
        for (int i = 0; i < nDtos; i ++) {
            list.add(new CombatAttributeDto(value, value, value, value));
        }
        return list;
    }
}
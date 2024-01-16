package me.minkh.app.domain.engraving;

import lombok.Getter;
import me.minkh.app.dto.engraving.CombatAttributeDto;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static me.minkh.app.service.LostArkConstants.*;

@Getter
public class CombatAttribute {

    // 치명타 적중률
    private final AtomicReference<Double> criticalHitRate;

    // 치명타 피해량
    private final AtomicReference<Double> criticalDamage;

    // 공격력 증가량
    private final AtomicReference<Double> attackIncrease;

    // 이동속도 증가량
    private final AtomicReference<Double> speedIncrease;

    public CombatAttribute() {
        this.criticalHitRate = new AtomicReference<>(0.0);
        this.criticalDamage = new AtomicReference<>(0.0);
        this.attackIncrease = new AtomicReference<>(0.0);
        this.speedIncrease = new AtomicReference<>(0.0);
    }

    public void accumulate(List<CombatAttributeDto> combatAttributeDtos) {
        for (CombatAttributeDto combatAttributeDto : combatAttributeDtos) {
            this.accumulate(combatAttributeDto);
        }
    }

    private void accumulate(CombatAttributeDto dto) {
        this.criticalHitRate.updateAndGet(current -> current + dto.getCriticalHitRate());
        this.criticalDamage.updateAndGet(current -> current + dto.getCriticalDamage());
        this.attackIncrease.updateAndGet(current -> current + dto.getAttackIncrease());
        this.speedIncrease.updateAndGet(current -> current + dto.getSpeedIncrease());
    }

    // 치명타 적중률 변환, e.g. 65 -> 0.65, 1을 넘어가면 안된다.
    public void changeCriticalHitRate() {
        this.criticalHitRate.updateAndGet(current -> Math.min(current / 100 , 1));
    }

    // 치명타 피해량 변환 : { (기본값 200 + 계산값 - 100) / 100 }, 기본값이 200이기 때문에 1 밑으로 떨어지지 않는다.
    public void changeCriticalDamage() {
        this.criticalDamage.updateAndGet(current -> (BASIC_CRITICAL_DAMAGE + current - 100) / 100);
    }

    // 예리한 둔기 계산
    public double calcSharpBlunt() {
        // 예리한 둔기 적용 대미지 : (치명타 피해량 + 예리한 둔기 추가 치명타 피해량 0.5) * 치명타 적중률 + 1(default)
        double sharpBluntDamage = ((this.criticalDamage.get() + SHARP_BLUNT_CRITICAL_DAMAGE) * this.criticalHitRate.get()) + 1;

        // 기본 대미지 : 치명타 피해량 * 치명타 적중률 + 1(default)
        double basicDamage = (this.criticalDamage.get() * this.criticalHitRate.get()) + 1;

        double result = (sharpBluntDamage / basicDamage) * SHARP_BLUNT_ADJUSTMENT * 100 - 100;
        return round2(result);
    }

    // 돌격대장 계산
    public double calcBlitzCommander() {
        double result = this.speedIncrease.get() * BLITZ_COMMANDER_COEFFICIENT;
        // 돌격 대장의 최대 효율은 18
        return Math.min(BLITZ_COMMANDER_MAX_EFFICIENCY, round2(result));
    }

    // 저주받은 인형 계산
    public double calcCursedDoll(double attackIncrease) {
        double result = ((this.attackIncrease.get() + 100) / (this.attackIncrease.get() - attackIncrease + 100)) * 100 - 100;
        return round2(result);
    }

    public double round2(double value) {
        return (double) Math.round(value * 100) / 100;
    }
}

package me.minkh.app.domain.engraving;

import me.minkh.app.dto.engraving.CombatAttributeDto;

import java.util.List;

import static me.minkh.app.service.LostArkConstants.*;

public class CombatAttribute {

    // 치명타 적중률
    private double criticalHitRate;

    // 치명타 피해량
    private double criticalDamage;

    // 공격력 증가량
    private double attackIncrease;

    // 이동속도 증가량
    private double speedIncrease;

    public void accumulate(List<CombatAttributeDto> combatAttributeDtos) {
        for (CombatAttributeDto combatAttributeDto : combatAttributeDtos) {
            this.accumulate(combatAttributeDto);
        }
    }

    private void accumulate(CombatAttributeDto dto) {
        this.criticalHitRate += dto.getCriticalHitRate();
        this.criticalDamage += dto.getCriticalDamage();
        this.attackIncrease += dto.getAttackIncrease();
        this.speedIncrease += dto.getSpeedIncrease();
    }

    // 치명타 적중률 변환, e.g. 65 -> 0.65, 1을 넘어가면 안된다.
    public void changeCriticalHitRate() {
        this.criticalHitRate = Math.min(this.criticalHitRate / 100, 1);
    }

    // 치명타 피해량 변환 : { (기본값 200 + 계산값 - 100) / 100 }, 기본값이 200이기 때문에 1 밑으로 떨어지지 않는다.
    public void changeCriticalDamage() {
        this.criticalDamage = (BASIC_CRITICAL_DAMAGE + this.criticalDamage - 100) / 100;
    }

    // 예리한 둔기 계산
    public double calcSharpBlunt() {
        // 예리한 둔기 적용 대미지 : (치명타 피해량 + 예리한 둔기 추가 치명타 피해량 0.5) * 치명타 적중률 + 1(default)
        double sharpBluntDamage = ((this.criticalDamage + SHARP_BLUNT_CRITICAL_DAMAGE) * this.criticalHitRate) + 1;

        // 기본 대미지 : 치명타 피해량 * 치명타 적중률 + 1(default)
        double basicDamage = (this.criticalDamage * this.criticalHitRate) + 1;

        double result = (sharpBluntDamage / basicDamage) * SHARP_BLUNT_ADJUSTMENT * 100 - 100;
        return round2(result);
    }

    // 돌격대장 계산
    public double calcBlitzCommander() {
        double result = this.speedIncrease * BLITZ_COMMANDER_COEFFICIENT;
        // 돌격 대장의 최대 효율은 18
        return Math.min(BLITZ_COMMANDER_MAX_EFFICIENCY, round2(result));
    }

    // 저주받은 인형 계산
    public double calcCursedDoll(double attackIncrease) {
        double result = ((this.attackIncrease + 100) / (this.attackIncrease - attackIncrease + 100)) * 100 - 100;
        return round2(result);
    }

    public double round2(double value) {
        return (double) Math.round(value * 100) / 100;
    }
}

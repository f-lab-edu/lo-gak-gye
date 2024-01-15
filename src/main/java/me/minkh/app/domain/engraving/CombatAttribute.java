package me.minkh.app.domain.engraving;

import me.minkh.app.dto.engraving.CombatAttributeDto;

import java.util.List;

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
        this.criticalDamage = (200 + this.criticalDamage - 100) / 100;
    }

    // 예리한 둔기 계산
    public double calcSharpBlunt() {
        double result = (((((this.criticalDamage + 0.5) * this.criticalHitRate) + 1) / ((this.criticalDamage * this.criticalHitRate) + 1)) * 0.98) * 100 - 100;
        return round2(result);
    }

    // 돌격대장 계산
    public double calcBlitzCommander() {
        double result = this.speedIncrease * 0.45;
        // 돌격 대장의 최대 효율은 18
        return Math.min(18, round2(result));
    }

    // 저주받은 인형 계산
    public double calcCursedDoll(double attackIncrease) {
        double result = ((100 + this.attackIncrease) / (100 + this.attackIncrease - attackIncrease)) * 100 - 100;
        return round2(result);
    }

    public double round2(double value) {
        return (double) Math.round(value * 100) / 100;
    }
}

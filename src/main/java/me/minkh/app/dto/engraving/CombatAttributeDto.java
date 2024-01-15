package me.minkh.app.dto.engraving;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.minkh.app.domain.engraving.Artifact;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CombatAttributeDto {

    // 치명타 적중률
    private double criticalHitRate;

    // 치명타 피해량
    private double criticalDamage;

    // 공격력 증가량
    private double attackIncrease;

    // 이동속도 증가량
    private double speedIncrease;

    public CombatAttributeDto(Artifact artifact) {
        this.criticalHitRate = artifact.getCriticalHitRate();
        this.criticalDamage = artifact.getCriticalDamage();
        this.attackIncrease = artifact.getAttackIncrease();
        this.speedIncrease = artifact.getSpeedIncrease();
    }
}

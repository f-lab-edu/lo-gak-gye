package me.minkh.app.dto.engraving.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.minkh.app.domain.engraving.preset.Etc;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EtcDto {

    private double criticalDamage;

    private double criticalHitRate;

    private double attackIncrease;

    private double speedIncrease;

    public Etc toEntity() {
        return Etc.builder()
                .criticalDamage(this.criticalDamage)
                .criticalHitRate(this.criticalHitRate)
                .attackIncrease(this.attackIncrease)
                .speedIncrease(this.speedIncrease)
                .build();
    }
}

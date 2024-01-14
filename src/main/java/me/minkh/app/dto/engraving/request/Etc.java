package me.minkh.app.dto.engraving.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Etc {

    private double criticalDamage;

    private double criticalHitRate;

    private double attackIncrease;

    private double speedIncrease;
}

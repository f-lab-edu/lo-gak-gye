package me.minkh.app.domain.engraving;

import jakarta.persistence.Entity;
import lombok.Getter;
import me.minkh.app.domain.model.BaseEntity;

@Getter
@Entity
public class Artifact extends BaseEntity {

    private String name;

    private double criticalHitRate;

    private double criticalDamage;

    private double attackIncrease;

    private double speedIncrease;

    public Artifact() {
    }

    public Artifact(String name, double criticalHitRate, double criticalDamage, double attackIncrease, double speedIncrease) {
        this.name = name;
        this.criticalHitRate = criticalHitRate;
        this.criticalDamage = criticalDamage;
        this.attackIncrease = attackIncrease;
        this.speedIncrease = speedIncrease;
    }
}


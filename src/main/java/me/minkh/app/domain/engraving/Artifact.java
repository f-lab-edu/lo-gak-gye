package me.minkh.app.domain.engraving;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Artifact {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


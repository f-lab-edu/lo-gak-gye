package me.minkh.app.domain.engraving.preset;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minkh.app.domain.model.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class CombatStat extends BaseEntity {

    private String type;

    private int value;

    @ManyToOne
    @JoinColumn(name = "preset_id")
    private Preset preset;

    public void addPreset(Preset preset) {
        this.preset = preset;
    }
}


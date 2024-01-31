package me.minkh.app.domain.engraving.preset;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import me.minkh.app.domain.model.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Preset extends BaseEntity {

    private String artifact;

    @Embedded
    private Elixir elixir;

    @OneToMany
    @JoinColumn(name = "preset_id")
    private List<CombatStat> combatStats = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "preset_id")
    private List<Engraving> engravings = new ArrayList<>();

    @Embedded
    private Etc etc;
}

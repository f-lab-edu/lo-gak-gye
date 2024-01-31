package me.minkh.app.domain.engraving.preset;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.model.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Preset extends BaseEntity {

    private String artifact;

    @Embedded
    private Elixir elixir;

    @OneToMany(mappedBy = "preset", cascade = CascadeType.ALL)
    private List<CombatStat> combatStats = new ArrayList<>();

    @OneToMany(mappedBy = "preset", cascade = CascadeType.ALL)
    private List<Engraving> engravings = new ArrayList<>();

    @Embedded
    private Etc etc;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Preset(String artifact, Elixir elixir, Etc etc, List<CombatStat> combatStats, List<Engraving> engravings) {
        this.artifact = artifact;
        this.elixir = elixir;
        this.etc = etc;
        for (CombatStat combatStat : combatStats) {
            combatStat.addPreset(this);
        }
        this.combatStats = combatStats;
        for (Engraving engraving : engravings) {
            engraving.addPreset(this);
        }
        this.engravings = engravings;
    }

    public void addAccount(Account account) {
        this.account = account;
    }
}

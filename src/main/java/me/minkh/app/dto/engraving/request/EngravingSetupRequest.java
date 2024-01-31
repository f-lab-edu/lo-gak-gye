package me.minkh.app.dto.engraving.request;

import lombok.Data;
import me.minkh.app.domain.engraving.preset.CombatStat;
import me.minkh.app.domain.engraving.preset.Engraving;
import me.minkh.app.domain.engraving.preset.Preset;

import java.util.List;

@Data
public class EngravingSetupRequest {

    private String artifact;

    private ElixirDto elixir;

    private List<CombatStatDto> combatStats;

    private List<EngravingDto> engravings;

    private EtcDto etc;

    public Preset toEntity() {
        List<CombatStat> combatStats = this.getCombatStats()
                .stream()
                .map(CombatStatDto::toEntity)
                .toList();

        List<Engraving> engravings = this.getEngravings()
                .stream()
                .map(EngravingDto::toEntity)
                .toList();

        return new Preset(
                this.artifact,
                this.elixir.toEntity(),
                this.etc.toEntity(),
                combatStats,
                engravings
        );
    }
}


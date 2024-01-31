package me.minkh.app.dto.engraving.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.minkh.app.domain.engraving.preset.Preset;
import me.minkh.app.dto.engraving.request.CombatStatDto;
import me.minkh.app.dto.engraving.request.ElixirDto;
import me.minkh.app.dto.engraving.request.EngravingDto;
import me.minkh.app.dto.engraving.request.EtcDto;

import java.util.List;

@AllArgsConstructor
@Data
public class EngravingPresetResponse {

    private String artifact;

    private ElixirDto elixir;

    private List<CombatStatDto> combatStats;

    private List<EngravingDto> engravings;

    private EtcDto etc;

    public EngravingPresetResponse(Preset preset) {
        this.artifact = preset.getArtifact();
        this.elixir = new ElixirDto(
                preset.getElixir().getType(),
                preset.getElixir().getLevel(),
                preset.getElixir().getHeadOffensePower()
        );
        this.combatStats = preset.getCombatStats().stream()
                .map(combatStat -> new CombatStatDto(combatStat.getType(), combatStat.getValue()))
                .toList();
        this.engravings = preset.getEngravings().stream()
                .map(engraving -> new EngravingDto(engraving.getName(), engraving.getLevel()))
                .toList();
        this.etc = new EtcDto(
                preset.getEtc().getCriticalDamage(),
                preset.getEtc().getCriticalHitRate(),
                preset.getEtc().getAttackIncrease(),
                preset.getEtc().getSpeedIncrease()
        );
    }
}

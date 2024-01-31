package me.minkh.app.service.engraving;

import lombok.RequiredArgsConstructor;
import me.minkh.app.domain.engraving.CombatAttribute;
import me.minkh.app.domain.engraving.preset.*;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.dto.engraving.request.EngravingDto;
import me.minkh.app.dto.engraving.request.EngravingSetupRequest;
import me.minkh.app.dto.engraving.response.EngravingCalcResponse;
import me.minkh.app.dto.engraving.response.EngravingPresetResponse;
import me.minkh.app.service.engraving.converter.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static me.minkh.app.service.LostArkConstants.*;

@RequiredArgsConstructor
@Service
public class EngravingService {

    private final ArtifactConverter artifactConverter;
    private final ElixirConverter elixirConverter;
    private final CombatStatsConverter combatStatsConverter;
    private final EngravingsConverter engravingsConverter;
    private final EtcConverter etcConverter;

    private final CombatStatRepository combatStatRepository;
    private final EngravingRepository engravingRepository;
    private final PresetRepository presetRepository;

    public List<EngravingCalcResponse> calcEngravings(EngravingSetupRequest dto) {
        List<CombatAttributeDto> combatAttributeDtos = List.of(
                artifactConverter.convert(dto.getArtifact()),
                elixirConverter.convert(dto.getElixir()),
                combatStatsConverter.convert(dto.getCombatStats()),
                engravingsConverter.convert(dto.getEngravings()),
                etcConverter.convert(dto.getEtc())
        );

        CombatAttribute combatAttribute = new CombatAttribute();
        combatAttribute.accumulate(combatAttributeDtos);
        combatAttribute.changeCriticalHitRate();
        combatAttribute.changeCriticalDamage();

        double attackIncrease = this.getAttackIncrease(dto.getEngravings());

        return List.of(
                new EngravingCalcResponse(SHARP_BLUNT, combatAttribute.calcSharpBlunt()),
                new EngravingCalcResponse(BLITZ_COMMANDER, combatAttribute.calcBlitzCommander()),
                new EngravingCalcResponse(CURSED_DOLL, combatAttribute.calcCursedDoll(attackIncrease))
        );
    }

    private double getAttackIncrease(List<EngravingDto> engravings) {
        int level = 0;
        for (EngravingDto engraving : engravings) {
            String name = engraving.getName();
            if (name.equals(CURSED_DOLL)) {
                level = engraving.getLevel();
                return CURSED_DOLL_TO_ATTACK_INCREASE_MAP.get(level);
            }
        }
        return CURSED_DOLL_TO_ATTACK_INCREASE_MAP.get(level);
    }

    @Transactional
    public EngravingPresetResponse savePreset(EngravingSetupRequest request) {
        Preset preset = this.presetRepository.save(request.toEntity());
        return new EngravingPresetResponse(preset);
    }
}
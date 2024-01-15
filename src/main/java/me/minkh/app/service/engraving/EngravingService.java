package me.minkh.app.service.engraving;

import lombok.RequiredArgsConstructor;
import me.minkh.app.domain.engraving.CombatAttribute;
import me.minkh.app.dto.engraving.request.CalcEngravingRequest;
import me.minkh.app.dto.engraving.response.CalcEngravingResponse;
import me.minkh.app.dto.engraving.request.Engraving;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.service.engraving.converter.*;
import org.springframework.stereotype.Service;

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

    public List<CalcEngravingResponse> calcEngravings(CalcEngravingRequest dto) {
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
                new CalcEngravingResponse(SHARP_BLUNT, combatAttribute.calcSharpBlunt()),
                new CalcEngravingResponse(BLITZ_COMMANDER, combatAttribute.calcBlitzCommander()),
                new CalcEngravingResponse(CURSED_DOLL, combatAttribute.calcCursedDoll(attackIncrease))
        );
    }

    private double getAttackIncrease(List<Engraving> engravings) {
        int level = 0;
        for (Engraving engraving : engravings) {
            String name = engraving.getName();
            if (name.equals(CURSED_DOLL)) {
                level = engraving.getLevel();
                return cursedDollToAttackIncreaseMap.get(level);
            }
        }
        return cursedDollToAttackIncreaseMap.get(level);
    }
}
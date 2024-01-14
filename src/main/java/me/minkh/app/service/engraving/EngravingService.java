package me.minkh.app.service.engraving;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.engraving.CalcEngravingRequestDto;
import me.minkh.app.dto.engraving.CalcEngravingResponseDto;
import me.minkh.app.dto.engraving.Engraving;
import me.minkh.app.dto.engraving.EngravingStat;
import me.minkh.app.service.engraving.converter.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.minkh.app.service.LostArkConstants.*;

@RequiredArgsConstructor
@Service
public class EngravingService {

    private final ArtifactConverter artifactConverter;
    private final ElixirConverter elixirConverter;
    private final ProfileStatsConverter profileStatsConverter;
    private final EngravingsConverter engravingsConverter;
    private final EtcConverter etcConverter;

    public List<CalcEngravingResponseDto> calcEngravings(CalcEngravingRequestDto dto) {
        List<EngravingStat> engravingStats = List.of(
                artifactConverter.convert(dto.getArtifact()),
                elixirConverter.convert(dto.getElixir()),
                profileStatsConverter.convert(dto.getProfileStats()),
                engravingsConverter.convert(dto.getEngravings()),
                etcConverter.convert(dto.getEtc())
        );

        EngravingStat combinedStat = combineEngravingStats(engravingStats);
        return List.of(
                new CalcEngravingResponseDto(SHARP_BLUNT, calcSharpBlunt(combinedStat)),
                new CalcEngravingResponseDto(BLITZ_COMMANDER, calcBlitzCommander(combinedStat)),
                new CalcEngravingResponseDto(CURSED_DOLL, calcCursedDoll(combinedStat, dto.getEngravings()))
        );
    }

    public double calcSharpBlunt(EngravingStat engravingStat) {
        double criticalHitRate = engravingStat.getCriticalHitRate();
        double criticalDamage = engravingStat.getCriticalDamage();

        double result = (((((criticalDamage + 0.5) * criticalHitRate) + 1) / ((criticalDamage * criticalHitRate) + 1)) * 0.98) * 100 - 100;
        return round2(result);
    }

    private double calcCursedDoll(EngravingStat engravingStat, List<Engraving> engravings) {
        for (Engraving engraving : engravings) {
            String name = engraving.getName();
            if (name.equals(CURSED_DOLL)) {
                int level = engraving.getLevel();
                double cursedDollAttackIncrease = cursedDollToAttackIncreaseMap.get(level);
                double totalAttackIncrease = engravingStat.getAttackIncrease();

                double result = ((100 + totalAttackIncrease) / (100 + totalAttackIncrease - cursedDollAttackIncrease)) * 100 - 100;
                return round2(result);
            }
        }
        return 0;
    }

    public double calcBlitzCommander(EngravingStat engravingStat) {
        double speedIncrease = engravingStat.getSpeedIncrease();
        double result = speedIncrease * 0.45;
        // 돌격 대장의 최대 효율은 18
        return Math.min(18, round2(result));
    }

    public double round2(double value) {
        return (double) Math.round(value * 100) / 100;
    }

    private EngravingStat combineEngravingStats(List<EngravingStat> engravingStats) {
        EngravingStat engravingStat = new EngravingStat();
        for (EngravingStat stat : engravingStats) {
            engravingStat.setCriticalHitRate(engravingStat.getCriticalHitRate() + stat.getCriticalHitRate());
            engravingStat.setCriticalDamage(engravingStat.getCriticalDamage() + stat.getCriticalDamage());
            engravingStat.setAttackIncrease(engravingStat.getAttackIncrease() + stat.getAttackIncrease());
            engravingStat.setSpeedIncrease(engravingStat.getSpeedIncrease() + stat.getSpeedIncrease());
        }

        // 치명타 적중률 변환 : 65 -> 0.65, 1을 넘어가면 안된다.
        double hitRate = Math.min(engravingStat.getCriticalHitRate() / 100, 1);
        engravingStat.setCriticalHitRate(hitRate);

        // 치명타 피해량 변환 : (기본값 200 + 계산값 - 100) / 100, 기본값이 200이기 때문에 1 밑으로 떨어지지 않는다.
        double criticalDamage = ((200 + engravingStat.getCriticalDamage()) - 100) / 100;
        engravingStat.setCriticalDamage(criticalDamage);

        return engravingStat;
    }
}
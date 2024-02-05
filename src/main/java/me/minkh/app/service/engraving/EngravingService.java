package me.minkh.app.service.engraving;

import lombok.RequiredArgsConstructor;
import me.minkh.app.domain.account.Account;
import me.minkh.app.domain.account.AccountRepository;
import me.minkh.app.domain.engraving.CombatAttribute;
import me.minkh.app.domain.engraving.preset.Preset;
import me.minkh.app.domain.engraving.preset.PresetRepository;
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

    private final PresetRepository presetRepository;
    private final AccountRepository accountRepository;

    private static final String INVALID_REQUEST_MESSAGE = "은 올바르지 않은 요청입니다.";
    private static final String MAX_PRESETS_EXCEEDED_MESSAGE = "프리셋은 5개 이상 초과할 수 없습니다.";
    private static final int MAX_PRESETS = 5;

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
    public EngravingPresetResponse savePreset(EngravingSetupRequest request, Long accountId) {
        Account account = this.findAccountById(accountId);
        List<Preset> presets = this.presetRepository.findByAccount(account);
        if (presets.size() >= MAX_PRESETS) {
            throw new IllegalArgumentException(MAX_PRESETS_EXCEEDED_MESSAGE);
        }

        Preset entity = request.toEntity();
        entity.addAccount(account);

        Preset preset = this.presetRepository.save(entity);
        return new EngravingPresetResponse(preset);
    }

    public List<EngravingPresetResponse> getPresets(Long accountId) {
        Account account = this.findAccountById(accountId);
        List<Preset> presets = this.presetRepository.findByAccount(account);
        return presets.stream()
                .map(EngravingPresetResponse::new)
                .toList();
    }

    public EngravingPresetResponse getPreset(Long presetId, Long accountId) {
        Account account = this.findAccountById(accountId);
        Preset preset = this.findPresetByIdAndAccount(presetId, account);
        return new EngravingPresetResponse(preset);
    }

    @Transactional
    public EngravingPresetResponse deletePreset(Long presetId, Long accountId) {
        Account account = this.findAccountById(accountId);
        Preset preset = this.findPresetByIdAndAccount(presetId, account);
        this.presetRepository.delete(preset);
        return new EngravingPresetResponse(preset);
    }

    private Account findAccountById(Long accountId) {
        return this.accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException(accountId + INVALID_REQUEST_MESSAGE));
    }

    private Preset findPresetByIdAndAccount(Long presetId, Account account) {
        return this.presetRepository.findByIdAndAccount(presetId, account)
                .orElseThrow(() -> new IllegalArgumentException(presetId + INVALID_REQUEST_MESSAGE));
    }
}
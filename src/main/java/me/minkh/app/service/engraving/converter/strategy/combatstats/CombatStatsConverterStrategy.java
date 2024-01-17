package me.minkh.app.service.engraving.converter.strategy.combatstats;

import me.minkh.app.dto.engraving.CombatAttributeDto;

public interface CombatStatsConverterStrategy {

    boolean supports(String type);

    void updateCombatAttributeDto(CombatAttributeDto dto, int value);

}

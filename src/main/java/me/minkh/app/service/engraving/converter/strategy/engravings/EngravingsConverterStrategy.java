package me.minkh.app.service.engraving.converter.strategy.engravings;

import me.minkh.app.dto.engraving.CombatAttributeDto;

public interface EngravingsConverterStrategy {

    void updateCombatAttributeDto(CombatAttributeDto dto, int level);

}

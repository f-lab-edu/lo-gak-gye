package me.minkh.app.service.engraving.converter.strategy.engravings;

import me.minkh.app.dto.engraving.CombatAttributeDto;

public interface EngravingsConverterStrategy {

    boolean supports(String name);

    void updateCombatAttributeDto(CombatAttributeDto dto, int level);

}

package me.minkh.app.service.engraving.converter.strategy.elixir;

import me.minkh.app.dto.engraving.CombatAttributeDto;

public interface ElixirConverterStrategy {

    boolean supports(String type, int level);

    void updateCombatAttributeDto(CombatAttributeDto dto);
}

package me.minkh.app.service.engraving.converter;

import lombok.RequiredArgsConstructor;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.dto.engraving.request.Elixir;
import me.minkh.app.service.engraving.converter.strategy.elixir.ElixirConverterStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.minkh.app.service.LostArkConstants.ELIXIR_TO_ATTACK_INCREASE_MAP;

@RequiredArgsConstructor
@Service
public class ElixirConverter {

    private final List<ElixirConverterStrategy> strategies;

    public CombatAttributeDto convert(Elixir elixir) {
        CombatAttributeDto result = new CombatAttributeDto();
        int headOffensePower = elixir.getHeadOffensePower();
        result.setAttackIncrease(ELIXIR_TO_ATTACK_INCREASE_MAP.get(headOffensePower));
        for (ElixirConverterStrategy strategy : strategies) {
            if (!strategy.supports(elixir.getType(), elixir.getLevel())) continue;
            strategy.updateCombatAttributeDto(result);
        }
        return result;
    }
}

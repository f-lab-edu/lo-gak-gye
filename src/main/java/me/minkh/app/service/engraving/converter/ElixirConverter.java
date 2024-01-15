package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.request.Elixir;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.service.engraving.converter.strategy.elixir.ElixirConverterStrategy;
import me.minkh.app.service.engraving.converter.strategy.elixir.ExpertStrategy;
import me.minkh.app.service.engraving.converter.strategy.elixir.VanGuardStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static me.minkh.app.service.LostArkConstants.*;

@Service
public class ElixirConverter {

    private final Map<String, ElixirConverterStrategy> strategyContext = new HashMap<>();

    public ElixirConverter() {
        strategyContext.put(VANGUARD, new VanGuardStrategy());
        strategyContext.put(EXPERT, new ExpertStrategy());
    }

    public CombatAttributeDto convert(Elixir elixir) {
        CombatAttributeDto result = new CombatAttributeDto();

        int headOffensePower = elixir.getHeadOffensePower();
        result.setAttackIncrease(ELIXIR_TO_ATTACK_INCREASE_MAP.get(headOffensePower));

        ElixirConverterStrategy strategy = this.strategyContext.get(elixir.getType());
        if (strategy != null && strategy.supports(elixir.getLevel())) {
            strategy.updateCombatAttributeDto(result);
        }

        return result;
    }
}

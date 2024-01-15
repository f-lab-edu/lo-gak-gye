package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.request.Engraving;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.service.engraving.converter.strategy.engravings.AdrenalineStrategy;
import me.minkh.app.service.engraving.converter.strategy.engravings.CursedDollStrategy;
import me.minkh.app.service.engraving.converter.strategy.engravings.EngravingsConverterStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.minkh.app.service.LostArkConstants.*;

@Service
public class EngravingsConverter {

    private final Map<String, EngravingsConverterStrategy> strategyContext = new HashMap<>();

    public EngravingsConverter() {
        strategyContext.put(CURSED_DOLL, new CursedDollStrategy());
        strategyContext.put(ADRENALINE, new AdrenalineStrategy());
    }

    public CombatAttributeDto convert(List<Engraving> engravings) {
        CombatAttributeDto result = new CombatAttributeDto();
        for (Engraving engraving : engravings) {
            EngravingsConverterStrategy strategy = this.strategyContext.get(engraving.getName());

            if (strategy == null) continue;
            strategy.updateCombatAttributeDto(result, engraving.getLevel());
        }
        return result;
    }
}
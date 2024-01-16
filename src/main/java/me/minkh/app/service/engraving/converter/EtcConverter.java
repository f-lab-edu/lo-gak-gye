package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import me.minkh.app.dto.engraving.request.Etc;
import org.springframework.stereotype.Service;

@Service
public class EtcConverter {

    public CombatAttributeDto convert(Etc etc) {
        return new CombatAttributeDto(
                etc.getCriticalHitRate(),
                etc.getCriticalDamage(),
                etc.getAttackIncrease(),
                etc.getSpeedIncrease()
        );
    }

}

package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.EngravingStat;
import me.minkh.app.dto.engraving.Etc;
import org.springframework.stereotype.Service;

@Service
public class EtcConverter {

    public EngravingStat convert(Etc etc) {
        return new EngravingStat(
                etc.getCriticalHitRate(),
                etc.getCriticalDamage(),
                etc.getAttackIncrease(),
                etc.getSpeedIncrease()
        );
    }

}

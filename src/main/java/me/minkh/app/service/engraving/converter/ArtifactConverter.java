package me.minkh.app.service.engraving.converter;

import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.springframework.stereotype.Service;

import static me.minkh.app.service.LostArkConstants.*;

@Service
public class ArtifactConverter {

    public CombatAttributeDto convert(String artifact) {

        switch (artifact) {
            case NIGHTMARE, SALVATION, DOMINION -> {
                return new CombatAttributeDto(0, 0, 0, 0);
            }
            case ENTROPY -> {
                return new CombatAttributeDto(22, 65, 0, 0);
            }
            case HALLUCINATION -> {
                return new CombatAttributeDto(28, 0, 0, 0);
            }
            default -> throw new IllegalArgumentException(artifact + "가 올바르지 않습니다.");
        }
    }
}

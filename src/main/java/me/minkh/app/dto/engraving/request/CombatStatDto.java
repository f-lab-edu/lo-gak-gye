package me.minkh.app.dto.engraving.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.minkh.app.domain.engraving.preset.CombatStat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CombatStatDto {

    private String type;

    private int value;

    public CombatStat toEntity() {
        return CombatStat.builder()
                .type(this.type)
                .value(this.value)
                .build();
    }
}


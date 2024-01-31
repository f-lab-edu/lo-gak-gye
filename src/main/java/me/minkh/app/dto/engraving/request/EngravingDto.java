package me.minkh.app.dto.engraving.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.minkh.app.domain.engraving.preset.Engraving;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EngravingDto {

    private String name;

    private int level;

    public Engraving toEntity() {
        return Engraving.builder()
                .name(this.name)
                .level(this.level)
                .build();
    }
}

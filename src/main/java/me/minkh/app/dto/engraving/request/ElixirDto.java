package me.minkh.app.dto.engraving.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.minkh.app.domain.engraving.preset.Elixir;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ElixirDto {

    private String type;

    private int level;

    private int headOffensePower;

    public Elixir toEntity() {
        return Elixir.builder()
                .type(this.type)
                .level(this.level)
                .build();
    }
}

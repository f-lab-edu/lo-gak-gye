package me.minkh.app.domain.engraving.preset;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Elixir {

    private String type;

    private int level;

    private int headOffensePower;
}

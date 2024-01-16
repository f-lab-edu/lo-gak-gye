package me.minkh.app.dto.info;

import lombok.Data;

@Data
public class Engraving {

    private final String name;

    private final int level;

    public Engraving(String name, int level) {
        this.name = name;
        this.level = level;
    }
}

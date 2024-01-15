package me.minkh.app.dto.engraving.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CombatStat {

    private String type;

    private int value;
}


package me.minkh.app.dto.engraving.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ElixirDto {

    private String type;

    private int level;

    private int headOffensePower;
}

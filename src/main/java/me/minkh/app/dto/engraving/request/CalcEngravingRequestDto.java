package me.minkh.app.dto.engraving.request;

import lombok.Data;

import java.util.List;

@Data
public class CalcEngravingRequestDto {

    String artifact;

    Elixir elixir;

    List<CombatStats> combatStats;

    List<Engraving> engravings;

    Etc etc;
}

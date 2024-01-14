package me.minkh.app.dto.engraving;

import lombok.Data;

import java.util.List;

@Data
public class CalcEngravingRequestDto {

    String artifact;

    Elixir elixir;

    List<ProfileStat> profileStats;

    List<Engraving> engravings;

    Etc etc;
}

package me.minkh.app.dto.info;

import lombok.Getter;

import java.util.List;

@Getter
public class InfoResponseDto {

      private final String artifact;

      private final List<ProfileStatResponseDto> profileStats;

      private final List<EngravingResponseDto> engravings;

      public InfoResponseDto(EquipmentResponseDto equipment, List<ProfileStatResponseDto> profileStats, List<EngravingResponseDto> engravings) {
            this.artifact = equipment.getArtifact();
            this.profileStats = profileStats;
            this.engravings = engravings;
      }
}


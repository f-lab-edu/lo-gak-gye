package me.minkh.app.service.engraving.converter;

import lombok.RequiredArgsConstructor;
import me.minkh.app.domain.engraving.Artifact;
import me.minkh.app.domain.engraving.ArtifactRepository;
import me.minkh.app.dto.engraving.CombatAttributeDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArtifactConverter {

    private final ArtifactRepository artifactRepository;

    public CombatAttributeDto convert(String artifactName) {

        Artifact findArtifact = artifactRepository.findByName(artifactName)
                .orElseThrow(() -> new IllegalArgumentException(artifactName + "가 올바르지 않습니다."));

        return new CombatAttributeDto(findArtifact);
    }
}

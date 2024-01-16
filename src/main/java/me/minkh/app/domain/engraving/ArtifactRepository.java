package me.minkh.app.domain.engraving;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ArtifactRepository extends Repository<Artifact, Long> {

    void save(Artifact artifact);

    Optional<Artifact> findByName(String name);
}

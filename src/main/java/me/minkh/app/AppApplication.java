package me.minkh.app;

import lombok.RequiredArgsConstructor;
import me.minkh.app.domain.engraving.Artifact;
import me.minkh.app.domain.engraving.ArtifactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static me.minkh.app.service.LostArkConstants.*;

@EnableJpaAuditing
@RequiredArgsConstructor
@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    private final ArtifactRepository artifactRepository;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        this.artifactRepository.save(new Artifact(NIGHTMARE, 0, 0, 0, 0));
        this.artifactRepository.save(new Artifact(SALVATION, 0, 0, 0, 0));
        this.artifactRepository.save(new Artifact(DOMINION, 0, 0, 0, 0));
        this.artifactRepository.save(new Artifact(ENTROPY, 22, 65, 0, 0));
        this.artifactRepository.save(new Artifact(HALLUCINATION, 28, 0, 0, 0));
    }
}

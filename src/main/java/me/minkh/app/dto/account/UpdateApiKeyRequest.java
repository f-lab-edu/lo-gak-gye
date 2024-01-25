package me.minkh.app.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Builder
@Getter
public class UpdateApiKeyRequest {

    @NotBlank
    @Length(min = 20)
    private String apiKey;
}

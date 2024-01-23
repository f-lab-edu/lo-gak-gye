package me.minkh.app.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdateApiKeyRequest {

    @NotBlank
    @Length(min = 20)
    private String apiKey;
}

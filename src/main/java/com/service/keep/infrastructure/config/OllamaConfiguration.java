/**
 * author @bhupendrasambare
 * Date   :08/04/26
 * Time   :10:51 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "ollama")
@ConditionalOnProperty(name = "app.ollama", havingValue = "true")
public class OllamaConfiguration {

    @NotBlank
    private String baseUrl;

    @NotBlank
    private String model;
}

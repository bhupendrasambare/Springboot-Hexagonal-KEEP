/**
 * author @bhupendrasambare
 * Date   :08/04/26
 * Time   :10:51 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ollama")
@ConditionalOnProperty(name = "app.ollama", havingValue = "true")
public class OllamaConfiguration {

    private String baseUrl;
    private String model;
}

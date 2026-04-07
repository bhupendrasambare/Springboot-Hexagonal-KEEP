/**
 * author @bhupendrasambare
 * Date   :07/04/26
 * Time   :12:37 am
 * Project:Keep
 **/
package com.service.keep.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OllamaConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

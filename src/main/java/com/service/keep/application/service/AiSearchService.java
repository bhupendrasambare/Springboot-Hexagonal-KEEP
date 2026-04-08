/**
 * author @bhupendrasambare
 * Date   :07/04/26
 * Time   :12:33 am
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.keep.application.dto.response.MetadataResponse;
import com.service.keep.application.dto.response.SearchQueryResponse;
import com.service.keep.domain.port.outbound.AiSearchPort;
import com.service.keep.infrastructure.config.OllamaConfiguration;
import com.service.keep.infrastructure.dto.OllamaRequest;
import com.service.keep.infrastructure.dto.OllamaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AiSearchService implements AiSearchPort {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final OllamaConfiguration configuration;

    @Override
    public MetadataResponse generateMetadata(String title, String description) {

        try {
            String prompt = """
                Extract metadata from the note.

                Title: %s
                Description: %s

                Return ONLY JSON in this format:
                {
                  "tags": [],
                  "keywords": [],
                  "summary": ""
                }
                """.formatted(title, description);

            var request = new OllamaRequest(configuration.getModel(), prompt, false);

            var response = restTemplate.postForObject(
                    configuration.getBaseUrl() + "/api/generate",
                    request,
                    OllamaResponse.class
            );

            String cleaned = cleanJson(response.getResponse());

            return objectMapper.readValue(cleaned, MetadataResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            return new MetadataResponse();
        }
    }

    @Override
    public SearchQueryResponse parseSearchQuery(String promptText) {

        try {
            String prompt = """
                    Extract search keywords from the query.

                    Query: %s

                    Return ONLY JSON:
                    {
                      "tags": [],
                      "keywords": []
                    }
                    """.formatted(promptText);

            var request = new OllamaRequest(configuration.getModel(), prompt, false);

            var response = restTemplate.postForObject(
                    configuration.getBaseUrl() + "/api/generate",
                    request,
                    OllamaResponse.class
            );

            return objectMapper.readValue(response.getResponse(), SearchQueryResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            return new SearchQueryResponse();
        }
    }

    private String cleanJson(String raw) {
        if (raw == null) return "";

        // remove ```json and ```
        raw = raw.replaceAll("```json", "")
                .replaceAll("```", "");

        // trim spaces/newlines
        raw = raw.trim();

        // sometimes model adds text before/after JSON → extract only JSON block
        int start = raw.indexOf("{");
        int end = raw.lastIndexOf("}");

        if (start != -1 && end != -1) {
            raw = raw.substring(start, end + 1);
        }

        return raw;
    }
}

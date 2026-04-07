/**
 * author @bhupendrasambare
 * Date   :07/04/26
 * Time   :12:23 am
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import java.util.List;

public class MetadataResponse {
    List<String> tags;
    List<String> keywords;
    String summary;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public MetadataResponse(List<String> tags, List<String> keywords, String summary) {
        this.tags = tags;
        this.keywords = keywords;
        this.summary = summary;
    }

    public MetadataResponse() {
    }
}

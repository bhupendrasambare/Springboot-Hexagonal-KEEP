/**
 * author @bhupendrasambare
 * Date   :07/04/26
 * Time   :12:24 am
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import java.util.List;

public class SearchQueryResponse {
    List<String> tags;
    List<String> keywords;

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

    public SearchQueryResponse(List<String> tags, List<String> keywords) {
        this.tags = tags;
        this.keywords = keywords;
    }

    public SearchQueryResponse() {
    }
}

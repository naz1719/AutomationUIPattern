
package com.sample.rest.dto.etsy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class Pagination {

    @JsonProperty("effective_limit")
    private Integer effectiveLimit;
    @JsonProperty("effective_offset")
    private Integer effectiveOffset;
    @JsonProperty("next_offset")
    private Integer nextOffset;
    @JsonProperty("effective_page")
    private Integer effectivePage;
    @JsonProperty("next_page")
    private Integer nextPage;

    @JsonProperty("effective_limit")
    public Integer getEffectiveLimit() {
        return effectiveLimit;
    }

    @JsonProperty("effective_limit")
    public void setEffectiveLimit(Integer effectiveLimit) {
        this.effectiveLimit = effectiveLimit;
    }

    @JsonProperty("effective_offset")
    public Integer getEffectiveOffset() {
        return effectiveOffset;
    }

    @JsonProperty("effective_offset")
    public void setEffectiveOffset(Integer effectiveOffset) {
        this.effectiveOffset = effectiveOffset;
    }

    @JsonProperty("next_offset")
    public Integer getNextOffset() {
        return nextOffset;
    }

    @JsonProperty("next_offset")
    public void setNextOffset(Integer nextOffset) {
        this.nextOffset = nextOffset;
    }

    @JsonProperty("effective_page")
    public Integer getEffectivePage() {
        return effectivePage;
    }

    @JsonProperty("effective_page")
    public void setEffectivePage(Integer effectivePage) {
        this.effectivePage = effectivePage;
    }

    @JsonProperty("next_page")
    public Integer getNextPage() {
        return nextPage;
    }

    @JsonProperty("next_page")
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

}

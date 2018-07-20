
package com.sample.rest.dto.etsy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "limit",
    "offset",
    "page",
    "keywords",
    "sort_on",
    "sort_order",
    "min_price",
    "max_price",
    "color",
    "color_accuracy",
    "tags",
    "category",
    "location",
    "lat",
    "lon",
    "region",
    "geo_level",
    "accepts_gift_cards",
    "translate_keywords"
})
public class Params {

    @JsonProperty("limit")
    private String limit;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("page")
    private Object page;
    @JsonProperty("keywords")
    private String keywords;
    @JsonProperty("sort_on")
    private String sortOn;
    @JsonProperty("sort_order")
    private String sortOrder;
    @JsonProperty("min_price")
    private Object minPrice;
    @JsonProperty("max_price")
    private Object maxPrice;
    @JsonProperty("color")
    private Object color;
    @JsonProperty("color_accuracy")
    private Integer colorAccuracy;
    @JsonProperty("tags")
    private Object tags;
    @JsonProperty("category")
    private Object category;
    @JsonProperty("location")
    private Object location;
    @JsonProperty("lat")
    private Object lat;
    @JsonProperty("lon")
    private Object lon;
    @JsonProperty("region")
    private Object region;
    @JsonProperty("geo_level")
    private String geoLevel;
    @JsonProperty("accepts_gift_cards")
    private String acceptsGiftCards;
    @JsonProperty("translate_keywords")
    private String translateKeywords;

    @JsonProperty("limit")
    public String getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(String limit) {
        this.limit = limit;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("page")
    public Object getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Object page) {
        this.page = page;
    }

    @JsonProperty("keywords")
    public String getKeywords() {
        return keywords;
    }

    @JsonProperty("keywords")
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @JsonProperty("sort_on")
    public String getSortOn() {
        return sortOn;
    }

    @JsonProperty("sort_on")
    public void setSortOn(String sortOn) {
        this.sortOn = sortOn;
    }

    @JsonProperty("sort_order")
    public String getSortOrder() {
        return sortOrder;
    }

    @JsonProperty("sort_order")
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @JsonProperty("min_price")
    public Object getMinPrice() {
        return minPrice;
    }

    @JsonProperty("min_price")
    public void setMinPrice(Object minPrice) {
        this.minPrice = minPrice;
    }

    @JsonProperty("max_price")
    public Object getMaxPrice() {
        return maxPrice;
    }

    @JsonProperty("max_price")
    public void setMaxPrice(Object maxPrice) {
        this.maxPrice = maxPrice;
    }

    @JsonProperty("color")
    public Object getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(Object color) {
        this.color = color;
    }

    @JsonProperty("color_accuracy")
    public Integer getColorAccuracy() {
        return colorAccuracy;
    }

    @JsonProperty("color_accuracy")
    public void setColorAccuracy(Integer colorAccuracy) {
        this.colorAccuracy = colorAccuracy;
    }

    @JsonProperty("tags")
    public Object getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Object tags) {
        this.tags = tags;
    }

    @JsonProperty("category")
    public Object getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Object category) {
        this.category = category;
    }

    @JsonProperty("location")
    public Object getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Object location) {
        this.location = location;
    }

    @JsonProperty("lat")
    public Object getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Object lat) {
        this.lat = lat;
    }

    @JsonProperty("lon")
    public Object getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Object lon) {
        this.lon = lon;
    }

    @JsonProperty("region")
    public Object getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(Object region) {
        this.region = region;
    }

    @JsonProperty("geo_level")
    public String getGeoLevel() {
        return geoLevel;
    }

    @JsonProperty("geo_level")
    public void setGeoLevel(String geoLevel) {
        this.geoLevel = geoLevel;
    }

    @JsonProperty("accepts_gift_cards")
    public String getAcceptsGiftCards() {
        return acceptsGiftCards;
    }

    @JsonProperty("accepts_gift_cards")
    public void setAcceptsGiftCards(String acceptsGiftCards) {
        this.acceptsGiftCards = acceptsGiftCards;
    }

    @JsonProperty("translate_keywords")
    public String getTranslateKeywords() {
        return translateKeywords;
    }

    @JsonProperty("translate_keywords")
    public void setTranslateKeywords(String translateKeywords) {
        this.translateKeywords = translateKeywords;
    }

}

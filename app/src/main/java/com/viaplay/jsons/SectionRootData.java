package com.viaplay.jsons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionRootData {
    private String title;
    private String description;
    private String pageType;
    private String sectionId;
    private String href;

    @JsonProperty("_links")
    private Link link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Link{
        @JsonProperty("viaplay:sections")
        private List<Section> sectionList = new ArrayList<>();

        public List<Section> getSectionList() {
            return sectionList;
        }

        public void setSectionList(List<Section> sectionList) {
            this.sectionList = sectionList;
        }
    }
}

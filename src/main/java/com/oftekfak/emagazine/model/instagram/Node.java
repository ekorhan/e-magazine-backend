package com.oftekfak.emagazine.model.instagram;

import com.google.gson.annotations.SerializedName;

public class Node {
    @SerializedName("display_url")
    String displayUrl;
    @SerializedName("accessibility_caption")
    String accessibilityCaption;
    Owner owner;

    public String getDisplayUrl() {
        return displayUrl;
    }

    public String getAccessibilityCaption() {
        return accessibilityCaption;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public void setAccessibilityCaption(String accessibilityCaption) {
        this.accessibilityCaption = accessibilityCaption;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

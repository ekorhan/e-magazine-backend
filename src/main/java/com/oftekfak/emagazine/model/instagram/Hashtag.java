package com.oftekfak.emagazine.model.instagram;

import com.google.gson.annotations.SerializedName;

public class Hashtag {

    private long id;
    private String name;

    @SerializedName("profile_pic_url")
    private String profilePicUrl;

    @SerializedName("edge_hashtag_to_media")
    private EdgeHashTagToMedia edgeHashTagToMedia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public EdgeHashTagToMedia getEdgeHashTagToMedia() {
        return edgeHashTagToMedia;
    }

    public void setEdgeHashTagToMedia(EdgeHashTagToMedia edgeHashTagToMedia) {
        this.edgeHashTagToMedia = edgeHashTagToMedia;
    }
}

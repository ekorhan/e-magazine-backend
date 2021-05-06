package com.oftekfak.emagazine.model.user;

public class ProfileModel {
    private String mail;
    private String firstName;
    private String lastName;
    private long followerCount;
    private long followedCount;

    public ProfileModel() {
    }

    public ProfileModel(String mail, String firstName, String lastName) {
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(long followerCount) {
        this.followerCount = followerCount;
    }

    public long getFollowedCount() {
        return followedCount;
    }

    public void setFollowedCount(long followedCount) {
        this.followedCount = followedCount;
    }
}

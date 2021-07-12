package com.oftekfak.emagazine.model.user;

import com.oftekfak.emagazine.entity.AppUser;
import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.model.post.PostModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileModel {
    private Long id;
    private String userName;
    private String mail;
    private String firstName;
    private String lastName;
    private long followerCount;
    private long followedCount;
    private List<PostModel> posts = new ArrayList<>();

    public ProfileModel(AppUser appUser) {
        id = appUser.getId();
        userName = appUser.getUsername();
        mail = appUser.getEmail();
        firstName = appUser.getFirstName();
        lastName = appUser.getLastName();
    }

    public ProfileModel(AppUser appUser, List<PostEntity> postEntities) {
        this(appUser);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

    public void setPostsFromEntity(List<PostModel> postModels) {
        posts.addAll(postModels);
    }

}

package com.oftekfak.emagazine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_follow",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"main_user", "followed_user"}))
public class UserFollowEntity extends BaseEntity {
    private Long mainUser;
    private Long followedUser;
    private boolean isActive;

    @Column(name = "main_user")
    public Long getMainUser() {
        return mainUser;
    }

    public void setMainUser(Long mainUser) {
        this.mainUser = mainUser;
    }

    @Column(name = "followed_user")
    public Long getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(Long followedUser) {
        this.followedUser = followedUser;
    }

    @Column(name = "is_active")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

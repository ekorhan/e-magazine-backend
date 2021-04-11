package com.oftekfak.emagazine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_follow")
public class UserFollowEntity extends BaseEntity {
    private Long mainUser;
    private Long followedUser;

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
}

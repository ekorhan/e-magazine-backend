package com.oftekfak.emagazine.service;

import com.oftekfak.emagazine.entity.UserFollowEntity;
import com.oftekfak.emagazine.model.user.ProfileModel;

import java.util.List;

public interface IUserService {
    ProfileModel inquireSimpleProfileInfo(Long userId);

    ProfileModel inquireAllProfileInfo(Long userId);

    Long followUser(Long followedUserId);

    List<UserFollowEntity> inquireFollowedUsers(Long userId);

    List<UserFollowEntity> inquireFollowers(Long userId);

    void likePost(Long postId);

    void commentPost(Long postId, String comment);

    Long getAuthUserId();
}

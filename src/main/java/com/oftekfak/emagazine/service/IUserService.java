package com.oftekfak.emagazine.service;

import com.oftekfak.emagazine.entity.UserFollowEntity;
import com.oftekfak.emagazine.model.user.ProfileModel;

import java.util.List;

public interface IUserService {
    ProfileModel inquireSimpleProfileInfo(Long userId);

    ProfileModel inquireAllProfileInfo(Long userId);

    Long followUser(Long mainUserId, Long followedUserId);

    List<UserFollowEntity> inquireFollowedUsers(Long userId);

    List<UserFollowEntity> inquireFollowers(Long userId);

    void likePost(Long postId, Long userId);

    void commentPost(Long postId, Long userId, String comment);
}

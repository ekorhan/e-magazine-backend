package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.entity.UserFollowEntity;
import com.oftekfak.emagazine.model.user.ProfileModel;
import com.oftekfak.emagazine.repository.UserFollowRepository;
import com.oftekfak.emagazine.repository.UserRepository;
import com.oftekfak.emagazine.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFollowRepository userFollowRepository;

    public ProfileModel inquireUserProfileInformation(Long userId) {
        ProfileModel profileModel = userRepository.findProfileInformation(userId).orElse(null);

        if (Objects.nonNull(profileModel)) {
            long followedUserCount = inquireFollowedUsers(userId).size();
            long followersCount = inquireFollowers(userId).size();

            profileModel.setFollowedCount(followedUserCount);
            profileModel.setFollowerCount(followersCount);
        }

        return profileModel;
    }

    public Long followUser(Long mainUserId, Long followedUserId) {
        UserFollowEntity userFollowEntity = new UserFollowEntity();
        userFollowEntity.setMainUser(mainUserId);
        userFollowEntity.setFollowedUser(followedUserId);
        userFollowEntity.setCreatedAt(new Date());
        userFollowRepository.save(userFollowEntity);
        List<UserFollowEntity> followEntity = userFollowRepository.findByFollowedUser(followedUserId);
        if (Objects.nonNull(followEntity) && !followEntity.isEmpty())
            return (long) followEntity.size();
        return null;
    }

    @Override
    public List<UserFollowEntity> inquireFollowedUsers(Long userId) {
        return userFollowRepository.findByMainUser(userId);
    }

    @Override
    public List<UserFollowEntity> inquireFollowers(Long userId) {
        return userFollowRepository.findByFollowedUser(userId);
    }
}

package com.oftekfak.emagazine.service;

import com.oftekfak.emagazine.entity.UserFollowEntity;
import com.oftekfak.emagazine.model.user.ProfileModel;
import com.oftekfak.emagazine.repository.UserFollowRepository;
import com.oftekfak.emagazine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFollowRepository userFollowRepository;

    public ProfileModel inquireUserProfileInformation(Long userId) {
        return userRepository.findProfileInformation(userId).orElse(null);
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
}

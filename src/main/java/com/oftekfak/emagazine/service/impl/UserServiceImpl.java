package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.entity.CommentEntity;
import com.oftekfak.emagazine.entity.LikeEntity;
import com.oftekfak.emagazine.entity.UserFollowEntity;
import com.oftekfak.emagazine.model.user.ProfileModel;
import com.oftekfak.emagazine.repository.CommentRepository;
import com.oftekfak.emagazine.repository.LikeRepository;
import com.oftekfak.emagazine.repository.UserFollowRepository;
import com.oftekfak.emagazine.repository.UserRepository;
import com.oftekfak.emagazine.service.IPostService;
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

    @Autowired
    private IPostService postService;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CommentRepository commentRepository;

    public ProfileModel inquireSimpleProfileInfo(Long userId) {
        ProfileModel profileModel = userRepository.findSimpleProfileInfo(userId).orElse(null);
        if (Objects.nonNull(profileModel)) {
            setFollowCounts(profileModel);
        }
        return profileModel;
    }

    public ProfileModel inquireAllProfileInfo(Long userId) {
        ProfileModel profileModel = userRepository.findSimpleProfileInfo(userId).orElse(null);
        if (Objects.nonNull(profileModel)) {
            setFollowCounts(profileModel);
            setPosts(profileModel);
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

    @Override
    public void likePost(Long postId, Long userId) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setPostId(postId);
        likeEntity.setUserId(userId);
        likeRepository.save(likeEntity);
    }

    @Override
    public void commentPost(Long postId, Long userId, String comment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(comment);
        commentEntity.setPostId(postId);
        commentEntity.setUserId(userId);
        commentRepository.save(commentEntity);
    }

    private ProfileModel setFollowCounts(ProfileModel profileModel) {
        long followedUserCount = inquireFollowedUsers(profileModel.getId()).size();
        long followersCount = inquireFollowers(profileModel.getId()).size();

        profileModel.setFollowedCount(followedUserCount);
        profileModel.setFollowerCount(followersCount);

        return profileModel;
    }

    private ProfileModel setPosts(ProfileModel profileModel) {
        profileModel.setPostsFromEntity(postService.inquireUserPosts(profileModel.getId()));
        return profileModel;
    }
}

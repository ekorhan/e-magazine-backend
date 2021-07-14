package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.entity.AppUser;
import com.oftekfak.emagazine.entity.CommentRelEntity;
import com.oftekfak.emagazine.entity.LikeRelEntity;
import com.oftekfak.emagazine.entity.UserFollowEntity;
import com.oftekfak.emagazine.model.user.ProfileModel;
import com.oftekfak.emagazine.repository.CommentRepository;
import com.oftekfak.emagazine.repository.LikeRepository;
import com.oftekfak.emagazine.repository.UserFollowRepository;
import com.oftekfak.emagazine.repository.UserRepository;
import com.oftekfak.emagazine.security.AuthUserProvider;
import com.oftekfak.emagazine.service.IAppUserService;
import com.oftekfak.emagazine.service.IPostService;
import com.oftekfak.emagazine.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Autowired
    private IAppUserService appUserService;

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

    public Long followUser(Long followedUserId) {
        UserFollowEntity userFollowEntity;
        Long userId = getAuthUserId();
        Optional<UserFollowEntity> userFollowEntityOptional = userFollowRepository
                .findByMainUserAndFollowedUserAndActive(userId, followedUserId, true);
        if (userFollowEntityOptional.isPresent()) {
            userFollowEntity = userFollowEntityOptional.get();
            userFollowEntity.setActive(!userFollowEntity.isActive());
        } else {
            userFollowEntity = new UserFollowEntity();
            userFollowEntity.setMainUser(getAuthUserId());
            userFollowEntity.setFollowedUser(followedUserId);
            userFollowEntity.setCreatedAt(new Date());
        }
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
    public void likePost(Long postId) {
        Long userId = getAuthUserId();
        Optional<LikeRelEntity> likeRelEntityOptional = likeRepository.findByUserIdAndPostId(userId, postId);
        LikeRelEntity likeRelEntity;
        if (likeRelEntityOptional.isPresent()) {
            likeRelEntity = likeRelEntityOptional.get();
            likeRelEntity.setActive(!likeRelEntity.getActive());
        } else {
            likeRelEntity = new LikeRelEntity();
            likeRelEntity.setPostId(postId);
            likeRelEntity.setUserId(userId);
            likeRelEntity.setActive(true);
            likeRelEntity.setCreatedAt(new Date());
        }
        likeRepository.save(likeRelEntity);
    }

    @Override
    public void commentPost(Long postId, String comment) {
        CommentRelEntity commentRelEntity = new CommentRelEntity();
        commentRelEntity.setComment(comment);
        commentRelEntity.setPostId(postId);
        commentRelEntity.setUserId(getAuthUserId());
        commentRelEntity.setActive(true);
        commentRelEntity.setCreatedAt(new Date());
        commentRepository.save(commentRelEntity);
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

    public Long getAuthUserId() {
        String userName = AuthUserProvider.getAuthUser();
        AppUser appUser = appUserService.getAppUser(userName);
        return appUser.getId();
    }
}

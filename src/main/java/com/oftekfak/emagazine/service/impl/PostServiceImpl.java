package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.entity.UserFollowEntity;
import com.oftekfak.emagazine.model.post.PostModel;
import com.oftekfak.emagazine.repository.PostRepository;
import com.oftekfak.emagazine.security.AuthUserProvider;
import com.oftekfak.emagazine.service.IAppUserService;
import com.oftekfak.emagazine.service.IPostService;
import com.oftekfak.emagazine.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAppUserService appUserService;

    public PostEntity addPost(PostModel postModel) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postModel.getTitle());
        postEntity.setContent(postModel.getContent());
        postEntity.setUserId(appUserService.getAppUser(AuthUserProvider.getAuthUser()).getId());
        postEntity.setCreatedAt(new Date());
        return postRepository.save(postEntity);
    }

    @Override
    public List<PostEntity> inquireUserHomePagePosts() {
        List<UserFollowEntity> followedUsers = userService.inquireFollowedUsers(userService.getAuthUserId());
        ArrayList<Long> followedUserIds = new ArrayList<>();
        followedUsers.forEach(m -> followedUserIds.add(m.getFollowedUser()));
        return postRepository.findAllByUserIdOrderByCreatedAtDesc(followedUserIds);
    }

    @Override
    public List<PostEntity> inquireUserHomePagePostsForNewUser() {
        return postRepository.findAll();
    }

    @Override
    public List<PostEntity> inquireUserPosts(Long userId) {
        List<PostEntity> postEntities = postRepository.findByUserIdOrderByCreatedAtDesc(userId);
        if (postEntities == null)
            return new ArrayList<>();

        return postEntities;
    }
}

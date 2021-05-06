package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.entity.UserFollowEntity;
import com.oftekfak.emagazine.model.post.PostModel;
import com.oftekfak.emagazine.repository.PostRepository;
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

    public PostEntity addPost(PostModel postModel) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postModel.getTitle());
        postEntity.setContent(postModel.getContent());
        postEntity.setUserId(postModel.getUserId());
        postEntity.setCreatedAt(new Date());
        return postRepository.save(postEntity);
    }

    @Override
    public List<PostEntity> inquireUserHomePagePosts(Long userId) {
        List<UserFollowEntity> followedUsers = userService.inquireFollowedUsers(userId);
        ArrayList<Long> followedUserIds = new ArrayList<>();
        followedUsers.forEach( m -> followedUserIds.add(m.getFollowedUser()));
        List<PostEntity> postModels = postRepository.findAllById(followedUserIds);
        return postModels;
    }
}

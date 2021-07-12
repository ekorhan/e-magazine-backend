package com.oftekfak.emagazine.service;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.model.post.PostModel;

import java.util.List;

public interface IPostService {
    PostEntity addPost(PostModel postModel);

    List<PostEntity> inquireUserHomePagePosts();

    List<PostEntity> inquireUserHomePagePostsForNewUser();

    List<PostEntity> inquireUserPosts(Long userId);

    PostModel getPost(Long postId);

    int inquireLikeCount(Long postId);
}

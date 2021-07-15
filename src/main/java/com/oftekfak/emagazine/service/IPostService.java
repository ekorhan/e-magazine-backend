package com.oftekfak.emagazine.service;

import com.oftekfak.emagazine.model.post.PostModel;

import java.util.LinkedList;
import java.util.List;

public interface IPostService {
    PostModel addPost(PostModel postModel);

    LinkedList<PostModel> inquireUserHomePagePosts();

    List<PostModel> inquireUserHomePagePostsForNewUser();

    List<PostModel> inquireUserPosts(Long userId);

    PostModel getPost(Long postId);

    int inquireLikeCount(Long postId);

    List<PostModel> discovery();

    List<PostModel> simpleDiscovery();
}

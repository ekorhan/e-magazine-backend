package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.entity.LikeRelEntity;
import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.entity.UserFollowEntity;
import com.oftekfak.emagazine.model.post.PostModel;
import com.oftekfak.emagazine.repository.CommentRepository;
import com.oftekfak.emagazine.repository.LikeRepository;
import com.oftekfak.emagazine.repository.PostRepository;
import com.oftekfak.emagazine.security.AuthUserProvider;
import com.oftekfak.emagazine.service.IAppUserService;
import com.oftekfak.emagazine.service.IPostService;
import com.oftekfak.emagazine.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CommentRepository commentRepository;

    public PostModel addPost(PostModel postModel) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postModel.getTitle());
        postEntity.setContent(postModel.getContent());
        postEntity.setUserId(appUserService.getAppUser(AuthUserProvider.getAuthUser()).getId());
        postEntity.setCreatedAt(new Date());
        return new PostModel(postRepository.save(postEntity));
    }

    @Override
    public LinkedList<PostModel> inquireUserHomePagePosts() {
        List<UserFollowEntity> followedUsers = userService.inquireFollowedUsers(userService.getAuthUserId());
        ArrayList<Long> followedUserIds = new ArrayList<>();
        followedUsers.forEach(m -> followedUserIds.add(m.getFollowedUser()));
        LinkedList<PostModel> postModels = new LinkedList<>();
        postRepository.findAllByUserIdOrderByCreatedAtDesc(followedUserIds)
                .forEach(e -> postModels.add(new PostModel(e)));
        return postModels;
    }

    @Override
    public List<PostModel> inquireUserHomePagePostsForNewUser() {
        List<PostModel> postModels = new ArrayList<>();
        postRepository.findAll()
                .forEach(e -> postModels.add(new PostModel(e)));
        return postModels;
    }

    @Override
    public LinkedList<PostModel> inquireUserPosts(Long userId) {
        List<PostEntity> postEntities = postRepository.findByUserIdOrderByCreatedAtDesc(userId);
        if (postEntities == null)
            return new LinkedList<>();

        LinkedList<PostModel> postModels = new LinkedList<>();

        postEntities.forEach(e -> postModels.add(new PostModel(e)));

        return postModels;
    }

    @Override
    public PostModel getPost(Long postId) {
        Optional<PostModel> postModelOptional = postRepository.findAllPostFromPostId(postId);
        if (!postModelOptional.isPresent())
            return new PostModel();

        PostModel postModel = postModelOptional.get();

        postModel.setUserName(userService.inquireSimpleProfileInfo(postModel.getUserId()).getUserName());
        postModel.setLikeCount(inquireLikeCount(postId));
        Optional<LikeRelEntity> likeRelEntityOptional = likeRepository.findByUserIdAndPostId(userService.getAuthUserId(), postId);
        boolean isLiked = likeRelEntityOptional.isPresent() && likeRelEntityOptional.get().getActive();
        postModel.setLiked(isLiked);

        return postModel;
    }

    @Override
    public int inquireLikeCount(Long postId) {
        return likeRepository.likeCount(postId).size();
    }
}

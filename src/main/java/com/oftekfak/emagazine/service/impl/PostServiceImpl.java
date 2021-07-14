package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.entity.*;
import com.oftekfak.emagazine.model.post.CommentModel;
import com.oftekfak.emagazine.model.post.PostModel;
import com.oftekfak.emagazine.repository.CommentRepository;
import com.oftekfak.emagazine.repository.LikeRepository;
import com.oftekfak.emagazine.repository.PostRepository;
import com.oftekfak.emagazine.security.AuthUserProvider;
import com.oftekfak.emagazine.service.IAppUserService;
import com.oftekfak.emagazine.service.IPostService;
import com.oftekfak.emagazine.service.IUserService;
import com.oftekfak.emagazine.utils.ObjectUtils;
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
        postEntity.setPicture(postModel.getPicture());
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
        return postRepository.findAllPost();
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

        postModel.setLikeCount(inquireLikeCount(postId));
        Long userId = userService.getAuthUserId();
        Optional<LikeRelEntity> likeRelEntityOptional = likeRepository.findByUserIdAndPostId(userId, postId);
        boolean isLiked = likeRelEntityOptional.isPresent() && likeRelEntityOptional.get().getActive();
        postModel.setLiked(isLiked);
        LinkedList<CommentModel> commentModels = new LinkedList<>();
        for (CommentRelEntity e : commentRepository.findByPostIdAndActiveOrderByCreatedAtDesc(postId, true)) {
            CommentModel commentModel = new CommentModel();
            commentModel.setComment(e.getComment());
            commentModel.setOwner(e.getUserId().longValue() == userId.longValue());
            AppUser appUser = appUserService.getAppUser(e.getUserId());
            commentModel.setUserId(e.getUserId());
            commentModel.setUserName(appUser.getUsername());
            commentModel.setFullName(ObjectUtils.getFullNameFromAppUser(appUser));
            commentModel.setCreatedDate(e.getCreatedAt());
            commentModels.add(commentModel);
        }
        postModel.setComments(commentModels);
        postModel.setCommentCount(commentModels.size());

        return postModel;
    }

    @Override
    public int inquireLikeCount(Long postId) {
        return likeRepository.likeCount(postId).size();
    }
}

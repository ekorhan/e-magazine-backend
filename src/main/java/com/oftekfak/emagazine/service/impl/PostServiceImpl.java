package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.ai.discovery.BaseAI;
import com.oftekfak.emagazine.ai.discovery.Random;
import com.oftekfak.emagazine.ai.discovery.SimpleAI;
import com.oftekfak.emagazine.ai.discovery.UserStatsAI;
import com.oftekfak.emagazine.entity.*;
import com.oftekfak.emagazine.model.post.CommentModel;
import com.oftekfak.emagazine.model.post.PostModel;
import com.oftekfak.emagazine.repository.*;
import com.oftekfak.emagazine.security.AuthUserProvider;
import com.oftekfak.emagazine.service.IAppUserService;
import com.oftekfak.emagazine.service.IPostService;
import com.oftekfak.emagazine.service.IUserService;
import com.oftekfak.emagazine.utils.Constants;
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

    @Autowired
    private AIUserRelRepository aiUserRelRepository;

    @Autowired
    private AIRepository aiRepository;

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
        return postRepository.findPostsFromUserIds(followedUserIds);
    }

    @Override
    public List<PostModel> inquireUserHomePagePostsForNewUser() {
        return postRepository.findAllPost();
    }

    @Override
    public LinkedList<PostModel> inquireUserPosts(Long userId) {
        LinkedList<PostModel> postModels = postRepository.findPostsFromUserId(userId);
        if (postModels == null)
            return new LinkedList<>();

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

    @Override
    public List<PostModel> discovery() {
        BaseAI ai;
        Long userId = userService.getAuthUserId();

        String mostSuccessfulAI = findMostSuccessfulAIForUser(userId);
        switch (mostSuccessfulAI) {
            case Constants.AI_Short_Codes.a:
                ai = new UserStatsAI();
                break;
            case Constants.AI_Short_Codes.b:
                ai = new Random();
                break;
            default:
                ai = new SimpleAI();
                break;
        }

        List<PostModel> postModels = ai.getPosts(userId);

        if (postModels == null)
            return new ArrayList<>();

        return postModels;
    }

    @Override
    public List<PostModel> simpleDiscovery() {
        BaseAI ai = new SimpleAI();
        return ai.getPosts(userService.getAuthUserId());
    }

    private String findMostSuccessfulAIForUser(Long userId) {
        String mostSuccessfulAI = null;
        LinkedList<UserAIRel> userAIRel = aiUserRelRepository.getMostSuccessfulAI(userId);
        if (Objects.nonNull(userAIRel) && !userAIRel.isEmpty() && Objects.nonNull(userAIRel.get(0))) {
            Optional<String> e = aiRepository.findShortCodeById(userAIRel.get(0).getAiId());
            if (e.isPresent())
                mostSuccessfulAI = e.get();
        }

        return mostSuccessfulAI;
    }
}

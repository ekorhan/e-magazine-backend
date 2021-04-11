package com.oftekfak.emagazine.service;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.model.post.PostModel;
import com.oftekfak.emagazine.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostEntity addPost(PostModel postModel) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postModel.getTitle());
        postEntity.setContent(postModel.getContent());
        postEntity.setUserId(postModel.getUserId());
        postEntity.setCreatedAt(new Date());
        return postRepository.save(postEntity);
    }
}

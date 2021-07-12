package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.model.post.PostModel;
import com.oftekfak.emagazine.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/")
public class PostController {
    @Autowired
    private IPostService postService;

    @PostMapping(path = "post/upload")
    public ResponseEntity<PostEntity> addPost(@RequestBody PostModel postModel) {
        return ResponseEntity.ok(postService.addPost(postModel));
    }

    @PostMapping(path = "post/{postId}")
    public ResponseEntity<PostModel> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }
}

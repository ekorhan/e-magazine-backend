package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.model.post.PostModel;
import com.oftekfak.emagazine.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping(path = "post/upload")
    public ResponseEntity<PostEntity> addPost(@RequestBody PostModel postModel) {
        return ResponseEntity.ok(postService.addPost(postModel));
    }
}

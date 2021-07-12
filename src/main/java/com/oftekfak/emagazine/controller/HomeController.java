package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.model.post.PostModel;
import com.oftekfak.emagazine.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
public class HomeController {

    @Autowired
    IPostService postService;

    @GetMapping("/inquireHomePagePostsForNewUser")
    public ResponseEntity<List<PostModel>> inquireHomePagePostsForNewUser() {
        return ResponseEntity.ok(postService.inquireUserHomePagePostsForNewUser());
    }

    @GetMapping("/inquireHomePagePost")
    public ResponseEntity<LinkedList<PostModel>> inquireHomePagePosts() {
        return ResponseEntity.ok(postService.inquireUserHomePagePosts());
    }
}

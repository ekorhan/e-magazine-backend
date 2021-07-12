package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
public class HomeController {

    @Autowired
    IPostService postService;

    @GetMapping("/inquireHomePagePostsForNewUser")
    public ResponseEntity<List<PostEntity>> inquireHomePagePostsForNewUser() {
        return ResponseEntity.ok(postService.inquireUserHomePagePostsForNewUser());
    }

    @GetMapping("/inquireHomePagePost")
    public ResponseEntity<List<PostEntity>> inquireHomePagePosts() {
        return ResponseEntity.ok(postService.inquireUserHomePagePosts());
    }
}

package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
public class HomeController {

    @Autowired
    private IPostService postService;

    @GetMapping("/inquireHomePagePosts")
    public ResponseEntity<List<PostEntity>> inquireHomePagePosts(@RequestParam Long userId) {
        return ResponseEntity.ok(postService.inquireUserHomePagePosts(userId));
    }

    @GetMapping
    public String mainPage() {
        return "main page";
    }

    @GetMapping(path = "/home")
    public String homePage() {
        return "home page";
    }

    @GetMapping(path = "/home1")
    public String homePage1() {
        return "1";
    }

    @GetMapping(path = "/home2")
    public String homePage2() {
        return "2";
    }

    @GetMapping(path = "/home3")
    public String homePage3() {
        return "3";
    }

    @GetMapping(path = "/home4")
    public String homePage4() {
        return "4";
    }
}

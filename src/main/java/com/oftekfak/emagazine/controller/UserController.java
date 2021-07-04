package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping(path = "/follow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> followUser(@RequestParam Long mainUserId, @RequestParam Long followedUserId) {
        return ResponseEntity.ok(userService.followUser(mainUserId, followedUserId));
    }

    @PostMapping(path = "/likePost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> likePost(Long postId, Long userId) {
        try {
            userService.likePost(postId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/commentPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> commentPost(Long postId, Long userId, String comment) {
        try {
            userService.commentPost(postId, userId, comment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

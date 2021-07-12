package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping(path = "/follow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> followUser(@RequestParam Long followedUserId) {
        return ResponseEntity.ok(userService.followUser(followedUserId));
    }

    @PostMapping(path = "/likePost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> likePost(@RequestParam Long postId) {
        try {
            userService.likePost(postId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/commentPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> commentPost(Long postId, String comment) {
        try {
            userService.commentPost(postId, comment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

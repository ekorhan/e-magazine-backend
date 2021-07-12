package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.model.post.PostIdModel;
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
    public ResponseEntity<HttpStatus> likePost(@RequestBody PostIdModel postIdModel) {
        try {
            userService.likePost(postIdModel.getPostId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/commentPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> commentPost(@RequestBody PostIdModel postIdModel) {
        try {
            userService.commentPost(postIdModel.getPostId(), postIdModel.getComment());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

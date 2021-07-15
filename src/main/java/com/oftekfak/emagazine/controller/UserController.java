package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.model.post.PostIdRequest;
import com.oftekfak.emagazine.model.user.UserIdRequest;
import com.oftekfak.emagazine.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping(path = "/follow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> followUser(@RequestBody UserIdRequest userIdRequest) {
        return ResponseEntity.ok(userService.followUser(userIdRequest.getId()));
    }

    @PostMapping(path = "/likePost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> likePost(@RequestBody PostIdRequest postIdRequest) {
        try {
            userService.likePost(postIdRequest.getPostId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/commentPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> commentPost(@RequestBody PostIdRequest postIdRequest) {
        try {
            userService.commentPost(postIdRequest.getPostId(), postIdRequest.getComment());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Long> likePost( ) {
        return ResponseEntity.ok(userService.likePost());
    }

    @PostMapping(path = "/commentPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> commentPost( ) {
        return ResponseEntity.ok(userService.commentPost());
    }
}

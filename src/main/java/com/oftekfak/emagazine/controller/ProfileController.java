package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.model.user.ProfileModel;
import com.oftekfak.emagazine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/profile/inquireUserInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileModel> inquireUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.inquireUserProfileInformation(userId));
    }
}

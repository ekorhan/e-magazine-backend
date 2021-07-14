package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.model.user.ProfileModel;
import com.oftekfak.emagazine.service.IUserService;
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
    private IUserService userService;

    @GetMapping(path = "/profile/inquireUserInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileModel> inquireUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.inquireAllProfileInfo(userId));
    }

    @GetMapping(path = "/profile/myProfile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileModel> myProfile() {
        return ResponseEntity.ok(userService.myProfile());
    }
}

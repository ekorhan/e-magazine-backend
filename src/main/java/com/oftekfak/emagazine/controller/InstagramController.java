package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.service.IInstagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/find")
public class InstagramController {
    @Autowired
    private IInstagramService instagramService;

    @GetMapping
    public List<String> findUsers(@RequestParam String hashTag) {
        return instagramService.findUsers(hashTag);
    }
}

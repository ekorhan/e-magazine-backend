package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.model.registration.RegistrationRequest;
import com.oftekfak.emagazine.model.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String registerPost(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}

package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.model.registration.RegistrationRequest;
import com.oftekfak.emagazine.model.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}

package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.model.registration.LoginRequest;
import com.oftekfak.emagazine.model.registration.LoginService;
import com.oftekfak.emagazine.model.registration.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping
    public ResponseEntity<TokenModel> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginService.login(request));
    }
}

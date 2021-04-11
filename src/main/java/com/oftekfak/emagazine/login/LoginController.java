package com.oftekfak.emagazine.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenModel> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginService.login(request));
    }
}

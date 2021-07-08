package com.oftekfak.emagazine.controller;

import com.oftekfak.emagazine.model.registration.LoginRequest;
import com.oftekfak.emagazine.model.registration.LoginService;
import com.oftekfak.emagazine.model.registration.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(path = "/login")
    public ResponseEntity<TokenModel> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(loginService.login(request));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

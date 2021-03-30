package com.oftekfak.emagazine.login;

import com.oftekfak.emagazine.appuser.AppUser;
import com.oftekfak.emagazine.appuser.AppUserService;
import com.oftekfak.emagazine.registration.token.ConfirmationToken;
import com.oftekfak.emagazine.registration.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    AppUserService appUserService;
    @Autowired
    ConfirmationTokenService confirmationTokenService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenModel login(LoginRequest request) {
        AppUser appUser = appUserService.getAppUser(request.getEmail());
        if (bCryptPasswordEncoder.matches(request.getPassword(), appUser.getPassword())) {
            String token = UUID.randomUUID().toString();

            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    appUser
            );

            confirmationTokenService.saveConfirmationToken(
                    confirmationToken);

            return new TokenModel(token, appUser.getId());
        } else {
            return new TokenModel();
        }
    }
}

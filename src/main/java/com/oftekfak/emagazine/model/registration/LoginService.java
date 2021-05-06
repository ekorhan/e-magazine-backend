package com.oftekfak.emagazine.model.registration;

import com.oftekfak.emagazine.entity.AppUser;
import com.oftekfak.emagazine.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
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
        if (Objects.isNull(appUser))
            throw new IllegalStateException("User not found");

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

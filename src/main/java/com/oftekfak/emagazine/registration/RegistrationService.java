package com.oftekfak.emagazine.registration;

import com.oftekfak.emagazine.appuser.AppUser;
import com.oftekfak.emagazine.appuser.AppUserRole;
import com.oftekfak.emagazine.appuser.AppUserService;
import com.oftekfak.emagazine.registration.token.ConfirmationToken;
import com.oftekfak.emagazine.registration.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    @Autowired
    AppUserService appUserService;
    @Autowired
    ConfirmationTokenService confirmationTokenService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String register(RegistrationRequest request) {
        String token = appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER

                )
        );

        confirmToken(token);

        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());

        return "confirmed";
    }
}

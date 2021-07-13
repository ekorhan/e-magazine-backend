package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.entity.AppUser;
import com.oftekfak.emagazine.model.registration.ConfirmationToken;
import com.oftekfak.emagazine.model.registration.ConfirmationTokenService;
import com.oftekfak.emagazine.repository.AppUserRepository;
import com.oftekfak.emagazine.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserServiceImpl implements IAppUserService {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return appUserRepository.inquireUserByEmail(email);
    }

    @Override
    public AppUser getAppUser(String email) {
        return appUserRepository.inquireUserByEmail(email);
    }

    @Override
    public AppUser getAppUser(Long userId) {
        return appUserRepository.getOne(userId);
    }

    @Override
    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    @Override
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    @Override
    public String getUserNameFromToken(String token) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenService.getToken(token);
        if (confirmationToken.isPresent() && Objects.nonNull(confirmationToken.get().getAppUser()))
            return confirmationToken.get().getAppUser().getEmail();

        return "";
    }
}

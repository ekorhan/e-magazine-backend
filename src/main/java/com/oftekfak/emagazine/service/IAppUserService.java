package com.oftekfak.emagazine.service;

import com.oftekfak.emagazine.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends UserDetailsService {
    AppUser getAppUser(String email);

    String signUpUser(AppUser appUser);

    int enableAppUser(String email);
}

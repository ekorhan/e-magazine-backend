package com.oftekfak.emagazine.service;

import com.oftekfak.emagazine.model.user.ProfileModel;
import com.oftekfak.emagazine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ProfileModel inquireUserProfileInformation(Long userId) {
        return userRepository.findProfileInformation(userId).orElse(null);
    }
}

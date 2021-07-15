package com.oftekfak.emagazine.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public final class AuthUserProvider {
    public static String getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication))
            throw new IllegalStateException("authentication user cannot be find");

        return authentication.getName();
    }
}
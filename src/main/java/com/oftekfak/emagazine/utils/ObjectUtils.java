package com.oftekfak.emagazine.utils;

import com.oftekfak.emagazine.entity.AppUser;

import java.util.Objects;

public final class ObjectUtils {
    public static String getFullNameFromAppUser(AppUser appUser) {
        if (Objects.isNull(appUser))
            return "";

        return appUser.getFirstName() + " " + appUser.getLastName();
    }
}

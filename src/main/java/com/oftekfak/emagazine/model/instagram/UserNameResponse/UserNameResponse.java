package com.oftekfak.emagazine.model.instagram.UserNameResponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserNameResponse implements Serializable {
    private Long id;
    @SerializedName("username")
    private String userName;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}

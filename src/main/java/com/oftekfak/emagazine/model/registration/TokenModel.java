package com.oftekfak.emagazine.model.registration;

public class TokenModel {
    private Boolean success;
    private String token;
    private long userId;

    public TokenModel() {
        this.success = false;
        this.token = null;
    }

    public TokenModel(String token, Long userId) {
        this.success = true;
        this.token = token;
        this.userId = userId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

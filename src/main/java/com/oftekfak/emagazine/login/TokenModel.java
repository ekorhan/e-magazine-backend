package com.oftekfak.emagazine.login;

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
}

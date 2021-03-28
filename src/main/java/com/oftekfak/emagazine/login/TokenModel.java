package com.oftekfak.emagazine.login;

public class TokenModel {
    private Boolean success;
    private String token;

    public TokenModel() {
        this.success = false;
        this.token = null;
    }

    public TokenModel(String token) {
        this.success = true;
        this.token = token;
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

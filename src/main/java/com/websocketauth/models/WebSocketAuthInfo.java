package com.websocketauth.models;

public class WebSocketAuthInfo {
    String authToken;

    public WebSocketAuthInfo(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}

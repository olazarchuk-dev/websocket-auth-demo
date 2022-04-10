package io.nuvalence.websocketauth.models;

public class WebSocketAuthInfo {
    String authToken;

    public WebSocketAuthInfo(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}

package com.websocketauth.controller;

import com.websocketauth.models.WebSocketAuthInfo;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@Slf4j
public class AuthenticationController {

    @GetMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public String getToken() {
        String websocketAuthToken = "1dfa537f-d46f-451e-9daa-ce26a58c6583";
        WebSocketAuthInfo webSocketAuthInfo = new WebSocketAuthInfo(websocketAuthToken);
        log.info("websocketAuthToken: {}, webSocketAuthInfo: {}", websocketAuthToken, webSocketAuthInfo);

        return websocketAuthToken;
    }
}

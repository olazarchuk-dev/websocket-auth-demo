package com.websocketauth.controller;

import com.websocketauth.models.Greeting;
import com.websocketauth.models.GreetingMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

@Controller
@Slf4j
public class GreetingController {

    @MessageMapping("/greeting")
    @SendTo("/messages/greeting")
    public Greeting greeting(GreetingMessage message) {
        log.info("Greeting: {}", message);

        return new Greeting("Hello, " + htmlEscape(message.getName()) + "!");
    }
}

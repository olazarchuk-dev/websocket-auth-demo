package io.nuvalence.websocketauth.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import java.util.Map;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpRequest;

@Component
public class WebSocketHandshakeAuthInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {
        String authToken = getAuthToken(request);

        if (!"1dfa537f-d46f-451e-9daa-ce26a58c6583".equals(authToken)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }

        return true;
    }

    String getAuthToken(ServerHttpRequest request) {
        try {
            return fromHttpRequest(request)
                    .build()
                    .getQueryParams().get("authentication").get(0);
        } catch (NullPointerException e) {
            return null;
        }
    }
}

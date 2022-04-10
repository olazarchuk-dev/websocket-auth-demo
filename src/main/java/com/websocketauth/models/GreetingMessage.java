package com.websocketauth.models;

public class GreetingMessage {
    String name;

    public GreetingMessage() {
    }

    public GreetingMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GreetingMessage{" +
                "name='" + name + '\'' +
                '}';
    }
}

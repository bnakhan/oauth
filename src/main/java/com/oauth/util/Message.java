package com.oauth.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String message;
    private String description;

    public Message(String message, String description) {
        this.message = message;
        this.description = description;
    }
}

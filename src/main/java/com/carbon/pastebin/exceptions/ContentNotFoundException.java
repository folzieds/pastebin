package com.carbon.pastebin.exceptions;

public class ContentNotFoundException extends RuntimeException{

    public ContentNotFoundException(String message) {
        super(message);
    }
}

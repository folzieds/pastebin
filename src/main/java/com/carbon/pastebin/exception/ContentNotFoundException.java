package com.carbon.pastebin.exception;

public class ContentNotFoundException extends RuntimeException{

    public ContentNotFoundException(String message) {
        super(message);
    }
}

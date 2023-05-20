package com.carbon.pastebin.exception;

public class ContentExpiredException extends RuntimeException{

    public ContentExpiredException(String message) {
        super(message);
    }
}

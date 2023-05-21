package com.carbon.pastebin.exceptions;

public class ContentExpiredException extends RuntimeException{

    public ContentExpiredException(String message) {
        super(message);
    }
}

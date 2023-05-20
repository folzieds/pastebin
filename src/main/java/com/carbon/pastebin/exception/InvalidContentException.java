package com.carbon.pastebin.exception;

public class InvalidContentException extends RuntimeException{

    public InvalidContentException(String message) {
        super(message);
    }
}

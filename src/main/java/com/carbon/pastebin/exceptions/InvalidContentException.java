package com.carbon.pastebin.exceptions;

public class InvalidContentException extends RuntimeException{

    public InvalidContentException(String message) {
        super(message);
    }
}

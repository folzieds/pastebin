package com.carbon.pastebin.exception.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContentExceptionResponse {

    private String status;
    private String error;
    private String message;
}

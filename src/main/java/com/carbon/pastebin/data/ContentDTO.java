package com.carbon.pastebin.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ContentDTO {

    private String text;

    private LocalDate expiryDate;
}

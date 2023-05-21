package com.carbon.pastebin.services;

import org.springframework.http.ResponseEntity;

public interface ContentService {

    String createContent(String text, String expiryDateStr);

    ResponseEntity<String> getSharedContent(String url);
}

package com.carbon.pastebin.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface ContentService {

    String createContent(String text, String expiryDateStr);

    ResponseEntity<String> getSharedContent(String url);
}

package com.carbon.pastebin.service;

public interface ContentService {

    String createContent(String text, String expiryDateStr);
}

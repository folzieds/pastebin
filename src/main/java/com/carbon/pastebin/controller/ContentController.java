package com.carbon.pastebin.controller;

import com.carbon.pastebin.service.ContentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContentController {

    private ContentService contentService;
}

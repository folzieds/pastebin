package com.carbon.pastebin.controllers;

import com.carbon.pastebin.services.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/create")
    public String createSharedContent(@RequestParam("text") String text,
                                      @RequestParam(value = "expiry", required = false) String expiryDateStr) {

       return contentService.createContent(text, expiryDateStr);

    }

    @GetMapping("/{url}")
    public ResponseEntity<String> retrieveSharedContent(@PathVariable String url) {
        return contentService.getSharedContent(url);
    }


}

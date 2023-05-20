package com.carbon.pastebin.service;

import com.carbon.pastebin.exception.ContentExpiredException;
import com.carbon.pastebin.exception.ContentNotFoundException;
import com.carbon.pastebin.model.Content;
import com.carbon.pastebin.repository.ContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class ContentServiceImpl implements ContentService{

    private ContentRepository contentRepository;

    @Value("${content.domain.name}")
    private String DOMAIN_NAME;

    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public String createContent(String text, String expiryDateStr) {
        Content content = new Content();
        content.setText(text);

        if (expiryDateStr != null) {
            LocalDate expiryDate = LocalDate.parse(expiryDateStr);
            content.setExpiryDate(expiryDate);
        }

        String url = getRandomString(15);
        content.setUrl(url);

        contentRepository.save(content);

        return DOMAIN_NAME + url;
    }


    private String getRandomString(int stringLength) {
        return RandomStringUtils.random(stringLength, true, true);
    }

    @Override
    public ResponseEntity getSharedContent(String url) {

        Optional<Content> optionalContent = contentRepository.findByUrl(url);

        if (optionalContent.isPresent()) {
            Content content = optionalContent.get();

            if (content.getExpiryDate() != null && content.getExpiryDate().isBefore(LocalDate.now())) {
                log.info("Content has expired...");
                throw new ContentExpiredException("Content has expired.");
            }

            return ResponseEntity.ok(content.getText());
        } else {
            log.info("Content was not found...");
            throw new ContentNotFoundException("Content was not found.");
        }
    }
}

package com.carbon.pastebin.services;

import com.carbon.pastebin.exceptions.ContentExpiredException;
import com.carbon.pastebin.exceptions.ContentNotFoundException;
import com.carbon.pastebin.exceptions.InvalidContentException;
import com.carbon.pastebin.models.Content;
import com.carbon.pastebin.repositories.ContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class ContentServiceImpl implements ContentService{

    private final ContentRepository contentRepository;

    @Value("${content.domain.name}")
    private String DOMAIN_NAME;

    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public String createContent(String text, String expiryDateStr) {
        validateForCreate(text);
        Content content = new Content();
        content.setText(text);

        if (expiryDateStr != null) {
            LocalDateTime expiryDate = LocalDateTime.parse(expiryDateStr);
            content.setExpiryDate(expiryDate);
        }else{
            content.setExpiryDate(LocalDateTime.now().plusHours(1L));
        }
        int urlLength = Math.max(text.length() % 20, 15);
        String url = getRandomString(urlLength);
        content.setUrl(url);

        contentRepository.save(content);

        return DOMAIN_NAME + url;
    }

    private void validateForCreate(String text) {
        if(text == null || text.isEmpty()){
            throw new InvalidContentException("text cannot be empty...");
        }
    }


    private String getRandomString(int stringLength) {
        return RandomStringUtils.random(stringLength, true, true);
    }

    @Override
    public ResponseEntity getSharedContent(String url) {

        Optional<Content> optionalContent = contentRepository.findByUrl(url);

        if (optionalContent.isPresent()) {
            Content content = optionalContent.get();

            if (content.getExpiryDate() != null && content.getExpiryDate().isBefore(LocalDateTime.now())) {
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

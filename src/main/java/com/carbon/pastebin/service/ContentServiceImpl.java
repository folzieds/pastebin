package com.carbon.pastebin.service;

import com.carbon.pastebin.model.Content;
import com.carbon.pastebin.repository.ContentRepository;
import lombok.extern.slf4j.Slf4j;
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

        contentRepository.save(content);

        return generateShortURL(content.getId());
    }

    private String generateShortURL(Long contentId) {
        // Implement your short URL generation logic here
        // This could involve encoding the ID or using an algorithm to create a unique short URL
        return "https://yourdomain.com/" + contentId;
    }

    private String getRandomString(int stringLength) {
        return RandomStringUtils.random(stringLength, true, true);
    }

    @Override
    public ResponseEntity<String> getSharedContent(String url) {

        Optional<Content> optionalContent = contentRepository.findByUrl(url);

        if (optionalContent.isPresent()) {
            Content content = optionalContent.get();

            if (content.getExpiryDate() != null && content.getExpiryDate().isBefore(LocalDate.now())) {
                log.info("Content has expired...");
                return ResponseEntity.status(HttpStatus.GONE).body("Content has expired.");
            }

            return ResponseEntity.ok(content.getText());
        } else {
            log.info("Content was not found...");
            return ResponseEntity.notFound().build();
        }
    }
}

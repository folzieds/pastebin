package com.carbon.pastebin.service;

import com.carbon.pastebin.model.Content;
import com.carbon.pastebin.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
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

    // Utility method to generate a short URL based on the content's ID
    private String generateShortURL(Long contentId) {
        // Implement your short URL generation logic here
        // This could involve encoding the ID or using an algorithm to create a unique short URL
        return "https://yourdomain.com/" + contentId;
    }
}

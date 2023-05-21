package com.carbon.pastebin.jobs;

import com.carbon.pastebin.models.Content;
import com.carbon.pastebin.repositories.ContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class ContentExpiryJob {

    private final ContentRepository contentRepository;

    public ContentExpiryJob(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void deleteExpiredContent() {
        log.info("running job to delete all expired content");
        LocalDateTime currentTime = LocalDateTime.now();
        List<Content> expiredContent = contentRepository.findByExpiryDateBefore(currentTime);
        contentRepository.deleteAll(expiredContent);
        log.info("job successfully ran...");
    }
}

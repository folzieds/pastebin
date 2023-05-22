package com.carbon.pastebin.jobs;

import com.carbon.pastebin.models.Content;
import com.carbon.pastebin.repositories.ContentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class ContentExpiryJobTest {

    @Test
    public void testDeleteExpiredContent() {
        // Mock dependencies
        ContentRepository sharedContentRepository = Mockito.mock(ContentRepository.class);
        ContentExpiryJob contentExpiryJob = new ContentExpiryJob(sharedContentRepository);

        // Create test data
        LocalDateTime currentDate = LocalDateTime.now();
        Content content1 = new Content();
        content1.setId(1L);
        content1.setExpiryDate(currentDate.minusDays(1)); // Expired content
        Content content2 = new Content();
        content2.setId(2L);
        content2.setExpiryDate(currentDate.plusDays(1)); // Valid content
        List<Content> allContent = Arrays.asList(content1, content2);

        // Mock repository behavior
        when(sharedContentRepository.findByExpiryDateBefore(currentDate)).thenReturn(Arrays.asList(content1));

        // Execute the cron job
        contentExpiryJob.deleteExpiredContent();

        // Verify that the expired content is deleted
        verify(sharedContentRepository).deleteAll(Arrays.asList(content1));
    }
}
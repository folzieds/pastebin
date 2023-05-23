package com.carbon.pastebin.services;

import com.carbon.pastebin.models.Content;
import com.carbon.pastebin.repositories.ContentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ContentService.class)
class ContentServiceTest {

    @MockBean
    private ContentRepository contentRepository;
    private ContentService contentService;

    @BeforeEach
    public void setup() throws Exception {
        contentService = new ContentServiceImpl(contentRepository);
        Field field = ContentServiceImpl.class.getDeclaredField("DOMAIN_NAME");
        field.setAccessible(true);
        field.set(contentService, "http://localhost:8090/api/");

    }

    @Test
    void createContent() {
        when(contentRepository.save(any(Content.class)))
                .thenReturn(new Content(1L, "This is a test content", LocalDateTime.now().plusHours(1L), "aeDLiMbqYXeK2PY"));
        String response = contentService.createContent("This is a test content", null);
        Assertions.assertFalse(response.isEmpty());
        Assertions.assertTrue(response.contains("http://localhost:8090/api/"));
        Assertions.assertEquals(response, "http://localhost:8090/api/aeDLiMbqYXeK2PY");
    }

    @Test
    void getSharedContent() {
        when(contentRepository.findByUrl("aeDLiMbqYXeK2PY")).thenReturn(Optional.of(new Content(1L, "This is a test content", LocalDateTime.now().plusHours(1L), "aeDLiMbqYXeK2PY")));
        ResponseEntity<String> response = contentService.getSharedContent("aeDLiMbqYXeK2PY");

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(String.class, Objects.requireNonNull(response.getBody()).getClass());
        Assertions.assertEquals(response.getBody(), "This is a test content");
    }
}
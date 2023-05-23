package com.carbon.pastebin.controllers;

import com.carbon.pastebin.services.ContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(ContentController.class)
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @Test
    void createSharedContent() throws Exception{
        when(contentService.createContent("Test Content", null)).thenReturn("http://localhost:9080/api/aeDLiMbqYXeK2PY");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/create")
                        .param("text", "Test Content")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("http://localhost:9080/api/aeDLiMbqYXeK2PY"));
    }

    @Test
    void retrieveSharedContent() throws Exception {
        when(contentService.getSharedContent("aeDLiMbqYXeK2PY"))
                .thenReturn(ResponseEntity.ok().body("This is a test"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/aeDLiMbqYXeK2PY")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("This is a test"));
    }
}
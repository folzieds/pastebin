package com.carbon.pastebin.controllers;

import com.carbon.pastebin.models.Content;
import com.carbon.pastebin.repositories.ContentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentRepository contentRepository;

    @Test
    void createSharedContent() throws Exception{
        when(contentRepository.save(any(Content.class))).thenReturn(new Content());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/create")
                        .param("text", "Test Content")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void retrieveSharedContent() {
    }
}
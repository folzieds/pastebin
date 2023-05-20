package com.carbon.pastebin.repository;

import com.carbon.pastebin.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    Optional<Content> findByUrl(String url);
}

package com.carbon.pastebin.repositories;

import com.carbon.pastebin.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    Optional<Content> findByUrl(String url);

    List<Content> findByExpiryDateBefore(LocalDateTime currentTime);
}

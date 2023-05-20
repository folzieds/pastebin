package com.carbon.pastebin.repository;

import com.carbon.pastebin.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}

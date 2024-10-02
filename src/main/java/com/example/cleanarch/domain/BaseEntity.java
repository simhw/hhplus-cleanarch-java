package com.example.cleanarch.domain;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class BaseEntity {
    @CreatedDate
    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}

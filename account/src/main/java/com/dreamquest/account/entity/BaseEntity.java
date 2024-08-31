package com.dreamquest.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private String createdBy;

    @Column(insertable = false)
    private LocalDateTime updatedAt;

    private String updatedBy;
}

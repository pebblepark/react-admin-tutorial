package com.example.demo.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Getter
public abstract class AbstractAuditEntity {
    @CreatedDate
    @Column(name="cre_time", updatable = false)
    private LocalDateTime creTime;

    @LastModifiedDate
    @Column(name="upd_time")
    private LocalDateTime updTime;
}

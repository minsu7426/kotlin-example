package com.example.backend.domain.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    protected var createdDtm: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    //@Column(nullable = false)
    protected var modifyDtm: LocalDateTime = LocalDateTime.now()

}
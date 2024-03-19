package com.example.springmealkit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 엔티티에 시간이 입력되도록 해주는 클래스입니다.
 * MappedSuperClass 어노테이션을 통해 이 클래스를 상속받는 여러 엔티티들이 이 클래스에 적힌 속성들 (시간 정보) 을 사용 할 수 있게 됩니다.
 * EntityListeners 는 엔티티가 변경됨을 감지해서, modifiedAt 속성을 업데이트 해 주기 위해서 사용합니다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeStamp {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;
}

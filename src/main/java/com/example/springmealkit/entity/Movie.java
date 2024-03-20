package com.example.springmealkit.entity;

import com.example.springmealkit.dto.MovieRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 영화 정보를 담은 엔티티입니다
 * title : 영화 제목
 * myReivew : 나의 한줄평
 * star : 내가 정한 이 영화의 별점
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie extends TimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title; // 영화제목

    @Column(name = "my_review", nullable = false)
    private String myReview; // 나만의 줄거리 요약

    @Column(name = "star", nullable = false)
    private int star; // 내가 매기는 평점
}

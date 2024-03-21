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
    private int star;

    /**
     * Movie 엔티티를 생성 해 주는 "생성자" 매서드입니다.
     * 이 매서드가 호출 되면, 리턴 타입인 "Movie" 엔티티 를 반환합니다
     * @param movieRequestDto 생성 요청 DTO
     */
    public Movie(MovieRequestDto movieRequestDto) {
        this.title = movieRequestDto.getTitle();
        this.myReview = movieRequestDto.getMyReview();
        this.star = movieRequestDto.getStar();
    }

    /**
     * JPA 의 변경 감지(Dirty Checking) 기능을 사용해서 Movie 엔티티의 정보를 업데이트 합니다.
     * @param movieRequestDto 업데이트 요청 DTO
     */
    public void update(MovieRequestDto movieRequestDto) {
        this.title = movieRequestDto.getTitle();
        this.myReview = movieRequestDto.getMyReview();
        this.star = movieRequestDto.getStar();
    }
}
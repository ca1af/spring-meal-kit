package com.example.springmealkit.dto;

import com.example.springmealkit.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 영화의 정보를 담고 있는 DTO 입니다.
 * Dto - Data Transfer Object 의 줄임말입니다.
 * 엔티티는 DB 와 관련이 깊은 객체이기 때문에 사용자가 이 엔티티의 정보를 바로 알게 되는 것은 위험합니다.
 * 따라서 사용자에게 입력을 받을 때, 응답을 줄 때 모두 Dto 를 활용하는 것이 좋습니다!
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class MovieResponseDto {

    private Long id;

    private String title;

    private String myReview;

    private int star;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public MovieResponseDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.myReview = movie.getMyReview();
        this.star = movie.getStar();
        this.createdAt = movie.getCreatedAt();
        this.modifiedAt = movie.getModifiedAt();
    }
}

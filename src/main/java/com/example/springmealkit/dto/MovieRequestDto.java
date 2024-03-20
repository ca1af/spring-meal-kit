package com.example.springmealkit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 영화 등록, 영화 수정에 필요한 DTO
 * Dto - Data Transfer Object 의 줄임말입니다.
 * 엔티티는 DB 와 관련이 깊은 객체이기 때문에 사용자가 이 엔티티의 정보를 바로 알게 되는 것은 위험합니다.
 * 따라서 사용자에게 입력을 받을 때, 응답을 줄 때 모두 Dto 를 활용하는 것이 좋습니다!
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {
    private String title; // 영화 제목

    private String myReview; // 나만의 줄거리 요약

    private int star;
}

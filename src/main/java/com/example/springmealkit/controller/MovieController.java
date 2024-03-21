package com.example.springmealkit.controller;

import com.example.springmealkit.dto.MovieResponseDto;

import java.util.List;

// TODO : 이 컨트롤러 완성해서 MovieControllerTest 테스트 전부 통과시키기
//  힌트 : [ @PathVariable, @RestController, @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @RequestBody ...]
//    위 어노테이션들을 잘 활용해서 구현하기!
public class MovieController {

    public MovieResponseDto getMovie(){
        // TODO : "/api/movies/{movieId}" 로 GET 요청을 보내면 movieId 에 해당하는 영화 조회에 성공
        return null;
    }

    public List<MovieResponseDto> getMovies(){
        // TODO : "/api/movies" 로 GET 요청을 보내면 모든 영화 리스트 조회에 성공
        return null;
    }

    public MovieResponseDto createMovie(){
        // TODO : "/api/movies" 로 POST 요청을 보내면 영화가 생성됨
        return null;
    }

    public Long updateMovie(){
        // TODO : "/api/movies/{movieId}" 로 PUT 요청을 보내면 movieId 에 해당하는 영화의 정보가 수정됨
        return null;
    }

    public Long deleteMovie(){
        // TODO : "/api/movies/{movieId}" 로 DELETE 요청을 보내면 movieId 에 해당하는 영화의 정보가 삭제됨
        return null;
    }
}

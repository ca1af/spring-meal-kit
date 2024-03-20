package com.example.springmealkit.service;

import com.example.springmealkit.dto.MovieRequestDto;
import com.example.springmealkit.dto.MovieResponseDto;
import com.example.springmealkit.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto){
        // TODO : repository 를 통해서 DB 에 Entity 가 저장되도록 해 보기
        return null;
    }

    public MovieResponseDto getMovie(Long movieId){
        // TODO : 저장된 영화 1 개의 정보를 DB에서 불러 오기
        return null;
    }
    @Transactional
    public Long updateMovie(Long movieId, MovieRequestDto movieRequestDto){
        // TODO : 영화의 정보를 수정하고, 수정 사항을 DB 에 저장되도록 해 보기 ( hint : JPA 변경 감지 )
        return null;
    }
    @Transactional
    public Long deleteMovie(Long movieId){
        // TODO : 영화 하나를 삭제 해 보기 (hint : 아이디를 통해서 영화 엔티티를 찾은 후 ~)
        return null;
    }

}
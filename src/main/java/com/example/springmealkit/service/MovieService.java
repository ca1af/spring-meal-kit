package com.example.springmealkit.service;

import com.example.springmealkit.dto.MovieRequestDto;
import com.example.springmealkit.dto.MovieResponseDto;
import com.example.springmealkit.entity.Movie;
import com.example.springmealkit.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 컨트롤러와 Repository 의 중간 계층으로, 사용자가 요청하면 -> 서비스를 거쳐서 -> db의 데이터를 읽거나, 저장하게 됩니다.
 * 서비스에서는 사용자의 요청에 따라서
 * 1. DB 에 데이터를 어떻게 저장 할 지 (ex : dto 를 DB에 저장될 entity 로 바꾸는 일)
 * 2. DB 의 데이터를 사용자에게 어떤 식으로 보여 줄 지 (ex : 반대로 엔티티를 DTO 로 바꾸는 일)
 * 등등, 이 밖에도 아주 많은 "비즈니스 로직" 을 직접 처리하는 레이어입니다.
 */
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * 로직 : 컨트롤러에서 제공받은 dto -> Movie 객체 생성 -> Repository에 저장
     * -> 저장된 Movie 정보를 ResponseDto로 return
     * @param movieRequestDto 컨트롤러에서 제공받은 dto 입니다.
     * @return 저장된 Movie 정보를 ResponseDto에 담아서 반환합니다.
     */
    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto){
        Movie movie = new Movie(movieRequestDto);
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponseDto(savedMovie);
    }

    /**
     * 로직 : Movie 정보를 담아줄 빈 List 생성 -> Repository에서 모든 Movie 정보 조회
     * -> 조회한 정보를 ResponseDto로 변환해 List에 담기 -> List 를 반환
     * @return 저장된 Movie 정보를 ResponseDto에 담아서 반환합니다.
     */
    public List<MovieResponseDto> getMovies(){
        List<MovieResponseDto> result = new ArrayList<>();
        List<Movie> movieList = movieRepository.findAll();
        for (Movie movie:movieList) {
            result.add(new MovieResponseDto(movie));
        }
        return result;
    }

    /**
     * 로직 : Movie Id와 변경할 내용을 전달받음 -> Movie Id로 Movie 조회
     * -> 찾은 Movie 객체를 변경 -> Repository에 저장
     * @param movieId 변경할 Movie Id를 입력합니다.
     * @param movieRequestDto 변경할 Movie 내용을 입력합니다.
     * @return 변경된 Movie Id를 반환합니다.
     */
    @Transactional
    public Long updateMovie(Long movieId, MovieRequestDto movieRequestDto){
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("영화 정보를 찾을 수 없습니다.")
        );
        movie.updateMovie(movieRequestDto);
        movieRepository.save(movie);
        return movieId;
    }

    /**
     * 로직 : Movie Id 조회 -> 해당 Movie 정보 삭제 -> 삭제된 Movie Id 반환
     * @param movieId 삭제할 Movie Id를 입력합니다.
     * @return 삭제된 Movie Id를 반환합니다.
     */
    @Transactional
    public Long deleteMovie(Long movieId){
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("영화 정보를 찾을 수 없습니다.")
        );
        movieRepository.delete(movie);
        return movieId;
    }

    public MovieResponseDto getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("영화 정보를 찾을 수 없습니다.")
        );

        return new MovieResponseDto(movie);
    }
}
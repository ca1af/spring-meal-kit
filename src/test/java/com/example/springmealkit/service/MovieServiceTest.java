package com.example.springmealkit.service;

import com.example.springmealkit.dto.MovieRequestDto;
import com.example.springmealkit.dto.MovieResponseDto;
import com.example.springmealkit.entity.Movie;
import com.example.springmealkit.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SpringBootTest
@Transactional
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    private static final MovieRequestDto TEST_REQUEST_DTO = new MovieRequestDto(
            "TEST",
            "TEST",
            5
    );

    @Nested
    @DisplayName("레벨 1 테스트입니다. 전부 통과하면 성공!")
    public class LevelOne {
        @AfterEach
        void tearDown() {
            movieRepository.deleteAll();
        }

        @Test
        @DisplayName("영화 생성 정보를 담은 DTO는 null 이 아니다")
        void createMovie_notNull() {
            MovieResponseDto movieResponseDto = movieService.createMovie(TEST_REQUEST_DTO);
            assertThat(movieResponseDto).isNotNull();
        }

        @Test
        @DisplayName("생성한 영화 엔티티가 DB에 성공적으로 저장된다.")
        void createMovie_shouldSavedMovieEntity() {
            movieService.createMovie(TEST_REQUEST_DTO);
            List<Movie> movieList = movieRepository.findAll();
            assertThat(movieList.size()).isEqualTo(4);
        }

        @Test
        @DisplayName("영화 정보를 담은 DTO 리스트 조회를 성공한다.")
        void getMovies() {
            List<MovieResponseDto> movies = movieService.getMovies();
            assertThat(movies.size()).isEqualTo(3);
        }

        @Test
        @DisplayName("DB에서 엔티티를 조회하고, 이 엔티티를 DTO로 반환한다")
        void getMovie() {
            MovieResponseDto movieResponseDto = movieService.getMovie(1L);

            assertThat(movieResponseDto).isNotNull();
            assertSoftly(
                    softly -> {
                        softly.assertThat(movieResponseDto.getId()).isEqualTo(1L);
                        softly.assertThat(movieResponseDto.getTitle()).isEqualTo("아바타");
                        softly.assertThat(movieResponseDto.getStar()).isEqualTo(5);
                        softly.assertThat(movieResponseDto.getMyReview()).isEqualTo(
                                "외계 행성에서 벌어지는 인간과 외계인의 대결. 압도적인 스케일과 특수효과!"
                        );
                    }
            );
        }

        @Test
        @DisplayName("엔티티 수정을 성공한다.")
        void updateMovie() {
            Movie movie = movieRepository.findById(1L).orElse(null);

            movieService.updateMovie(1L, TEST_REQUEST_DTO);

            assertThat(movie).isNotNull();
            assertSoftly(
                    softly -> {
                        softly.assertThat(movie.getId()).isEqualTo(1L);
                        softly.assertThat(movie.getTitle()).isEqualTo(TEST_REQUEST_DTO.getTitle());
                        softly.assertThat(movie.getStar()).isEqualTo(TEST_REQUEST_DTO.getStar());
                        softly.assertThat(movie.getMyReview()).isEqualTo(
                                TEST_REQUEST_DTO.getMyReview()
                        );
                    }
            );
        }

        @Test
        @DisplayName("엔티티 삭제를 성공한다.")
        void deleteMovie() {
            movieService.deleteMovie(1L);
            Movie movie = movieRepository.findById(1L).orElse(null);
            assertThat(movie).isNull();
            List<Movie> movieList = movieRepository.findAll();
            assertThat(movieList.size()).isEqualTo(2);
        }
    }
}
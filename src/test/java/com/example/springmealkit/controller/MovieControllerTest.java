package com.example.springmealkit.controller;

import com.example.springmealkit.dto.MovieRequestDto;
import com.example.springmealkit.dto.MovieResponseDto;
import com.example.springmealkit.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@MockBean(JpaMetamodelMappingContext.class)
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Nested
    @DisplayName("레벨 2 테스트입니다. 모두 통과하면 성공!")
    class LevelTwo{
        @Test
        @DisplayName("영화 1개 조회 API 호출 시 성공한다")
        void testGetMovie() throws Exception {
            MovieResponseDto movieResponseDto = new MovieResponseDto(1L, "Test Movie", "Test review", 5, LocalDateTime.now(), LocalDateTime.now());

            when(movieService.getMovie(1L)).thenReturn(movieResponseDto);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.title").exists());

            verify(movieService, times(1)).getMovie(1L);
        }

        @Test
        @DisplayName("모든 영화 리스트 조회 API 호출 시 성공한다")
        void testGetMovies() throws Exception {
            MovieResponseDto movieResponseDto = new MovieResponseDto(1L, "Test Movie", "Test review", 5, LocalDateTime.now(), LocalDateTime.now());

            when(movieService.getMovies()).thenReturn(Collections.singletonList(movieResponseDto));

            mockMvc.perform(MockMvcRequestBuilders.get("/api/movies")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].title").value("Test Movie"))
                    .andExpect(jsonPath("$[0].myReview").value("Test review"))
                    .andExpect(jsonPath("$[0].star").value(5));

            verify(movieService, times(1)).getMovies();
        }

        @Test
        @DisplayName("영화 저장 API 호출 시 성공한다")
        void testCreateMovie() throws Exception {
            MovieRequestDto movieRequestDto = new MovieRequestDto("Test Movie", "Test review", 5);

            MovieResponseDto movieResponseDto = new MovieResponseDto(1L, "Test Movie", "Test review", 5, LocalDateTime.now(), LocalDateTime.now());

            when(movieService.createMovie(any(MovieRequestDto.class))).thenReturn(movieResponseDto);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                            .content(asJsonString(movieRequestDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.title").value("Test Movie"))
                    .andExpect(jsonPath("$.myReview").value("Test review"))
                    .andExpect(jsonPath("$.star").value(5));

            verify(movieService, times(1)).createMovie(any(MovieRequestDto.class));
        }

        @Test
        @DisplayName("영화 수정 API 호출 시 성공한다")
        void testUpdateMovie() throws Exception {
            MovieRequestDto movieRequestDto = new MovieRequestDto("Updated Test Movie", "Updated review", 4);

            when(movieService.updateMovie(1L, movieRequestDto)).thenReturn(1L);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/movies/1")
                            .content(asJsonString(movieRequestDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            verify(movieService, times(1)).updateMovie(any(), any(MovieRequestDto.class));
        }

        @Test
        @DisplayName("영화 삭제 API 호출 시 성공한다")
        void testDeleteMovie() throws Exception {
            when(movieService.deleteMovie(1L)).thenReturn(1L);

            mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("1"));

            verify(movieService, times(1)).deleteMovie(1L);
        }

        private static String asJsonString(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
package com.example.springmealkit.controller;

import com.example.springmealkit.dto.MovieRequestDto;
import com.example.springmealkit.dto.MovieResponseDto;
import com.example.springmealkit.service.MovieService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RestController 어노테이션은 @Controller 어노테이션과 @ResponseBody 가 합쳐져 있는 어노테이션 이랍니다!
 * 우리는 RestController 어노테이션을 통해서 매서드에서 "데이터" 를 리턴 할 수 있어요. 예를 들면 Json 같은 것들이죠
 * 자바 객체를 Json으로 변환하는 일은 스프링에 내장되어 있는 Jackson 라이브러리에서 이뤄집니다. (이 부분이 궁금하시면 나중에 한 번 찾아보셔도 좋아요)
 */
@RestController
@RequestMapping("/api")
public class MovieController {

    /**
     * 우리가 만든 MovieService 서비스 (스프링 "빈" 으로 등록됩니다)
     */
    private final MovieService movieService;

    /**
     * 우리가 만든 MovieService 를 스프링이 DI(의존성 주입) 해주는 모습입니다. 이걸 IoC 라고 불러요
     * @param movieService 우리가 만드는 movieService
     */
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * movieId 에 해당하는 영화의 정보를 조회 합니다.
     * @param movieId 영화의 아이디
     * @return 영화의 정보
     */
    @GetMapping("/movies/{movieId}")
    public MovieResponseDto getMovie(@PathVariable Long movieId){
        return movieService.getMovie(movieId);
    }

    /**
     * 모든 Movie 정보를 조회할 때 사용합니다.
     * @return 모든 Movie 정보가 담긴 리스트를 반환합니다.
     */
    @GetMapping("/movies")
    public List<MovieResponseDto> getMovies(){
        return movieService.getMovies();
    }

    /**
     * 새로운 Movie 정보를 생성할 때 사용합니다.
     * @param movieRequestDto 영화 생성 요청 DTO
     * @return 생성된 Movie 정보가 담긴 객체를 반환합니다.
     */
    @PostMapping("/movies")
    public MovieResponseDto createMovie(@RequestBody MovieRequestDto movieRequestDto){
        return movieService.createMovie(movieRequestDto);
    }

    /**
     * Movie 정보를 변경할 때 사용합니다.
     * @param movieId 변경하고 싶은 Movie의 Id를 입력해줍니다.
     * @param movieRequestDto 변경하고 싶은 내용이 담긴 객체를 입력해줍니다.
     * @return 변경된 Movie Id값을 반환합니다.
     */
    @PutMapping("/movies/{movieId}")
    public Long updateMovie(@PathVariable Long movieId, @RequestBody MovieRequestDto movieRequestDto){
        return movieService.updateMovie(movieId, movieRequestDto);
    }

    /**
     * Movie 정보를 삭제할 때 사용합니다.
     * @param movieId 삭제할 Movie Id를 입력해줍니다.
     * @return 삭제된 Movie Id를 반환합니다.
     */
    @DeleteMapping("/movies/{movieId}")
    public Long deleteMovie(@PathVariable Long movieId){
        movieService.deleteMovie(movieId);
        return movieId;
    }
}

package com.example.springmealkit.repository;

import com.example.springmealkit.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 실제로 디비에게 정보를 요청하고, 받아오고, DB 에 정보를 쌓는 Repository 입니다.
 * JpaRepository 에 있는 다양한 기능들을 간단하게 사용 할 수 있습니다.
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
}

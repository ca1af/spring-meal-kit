package com.example.springmealkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 이 어노테이션을 사용함으로써 우리는 TimeStamp 엔티티가 Movie 엔티티를 감시하며 시간을 넣어주도록 할 수 있어요!
public class SpringMealKitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMealKitApplication.class, args);
    }

}

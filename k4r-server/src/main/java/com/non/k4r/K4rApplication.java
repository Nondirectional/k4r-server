package com.non.k4r;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.non.k4r.**.mapper")
@ComponentScan("com.non.k4r.**")
public class K4rApplication {

    public static void main(String[] args) {
        SpringApplication.run(K4rApplication.class, args);
    }

}

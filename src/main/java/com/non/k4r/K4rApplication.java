package com.non.k4r;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.non.k4r.**.mapper")
public class K4rApplication {

    public static void main(String[] args) {
        SpringApplication.run(K4rApplication.class, args);
    }

}

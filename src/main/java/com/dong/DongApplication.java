package com.dong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dong.mapper")
public class DongApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongApplication.class, args);
    }

}

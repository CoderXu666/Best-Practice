package com.biu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.biu.mapper")
@SpringBootApplication
public class BiuUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiuUserApplication.class, args);
    }

}

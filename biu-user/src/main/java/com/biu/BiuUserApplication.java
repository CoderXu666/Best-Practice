package com.biu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.TimeZone;

@EnableCaching
@MapperScan("com.biu.mapper")
@SpringBootApplication
public class BiuUserApplication {
    public static void main(String[] args) {
        // JVM 和 Linux 服务器时间保持一致
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(BiuUserApplication.class, args);
    }
}

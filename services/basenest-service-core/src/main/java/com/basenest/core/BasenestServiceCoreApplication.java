package com.basenest.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(" com.basenest.core.dao.mapper")
@ComponentScan("com.basenest.core")
public class BasenestServiceCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasenestServiceCoreApplication.class, args);
    }

}

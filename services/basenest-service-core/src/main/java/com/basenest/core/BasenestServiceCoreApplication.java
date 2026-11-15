package com.basenest.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName BasenestServiceCoreApplication
 * @Description Application bootstrap.
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.basenest.core")
public class BasenestServiceCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasenestServiceCoreApplication.class, args);
    }

}
package com.example.springrestdoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class SpringRestDocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestDocApplication.class, args);
    }

}

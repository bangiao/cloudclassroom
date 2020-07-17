package com.dingxin.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dingxin")
@SpringBootApplication
public class ClassroomWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassroomWebApplication.class, args);
    }

}

package com.demo.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class StringSecurityApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run =  SpringApplication.run(StringSecurityApplication.class, args);
        System.out.println("....");
    }

}

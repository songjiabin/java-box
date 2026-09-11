package com.example.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * jdbc 模块的启动类。
 *
 * <p>它的第二个作用是充当测试的配置类：{@code @SpringBootApplication} 本身就是一个
 * {@code @SpringBootConfiguration}，{@code @SpringBootTest} 会从测试类所在包开始，
 * 逐级向上查找这个注解。模块里如果没有它，测试就会直接报
 * "Unable to find a @SpringBootConfiguration"。
 *
 * <p>另外，测试类必须和本类在同一个包或子包下，Spring Boot 才能找到它。
 */
@SpringBootApplication
public class SpringbootDataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDataJdbcApplication.class, args);
    }
}

package com.x.y;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication 来标注一个主程序类
// 说明这是一个 SpringBoot 应用
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 以为是启动了一个方法，没想到启动了一个服务
        // 该方法返回一个 ConfigurableApplicationContext 对象
        // 参数一：应用入口的类； 参数二：命令行参数
        SpringApplication.run(Application.class, args);
    }
}

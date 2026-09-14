package com.example.druid.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 现在需要程序员自己为 DruidDataSource 绑定全局配置文件中的参数，再添加到容器中，
 * 而不再使用 SpringBoot 的自动生成；我们需要自己添加 DruidDataSource 组件到容器中，并绑定属性：
 */
@Configuration
public class DruidConfig {

    /*
           将自定义的 Druid 数据源添加到容器中，不再让 Spring Boot 自动创建
           绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource 从而让它们生效
           @ConfigurationProperties(prefix = "spring.datasource.druid")：作用就是将全局配置文件中
           前缀为 spring.datasource 的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
         */
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DruidDataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}

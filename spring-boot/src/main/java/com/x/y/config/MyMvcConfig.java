package com.x.y.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// 如果我们要扩展 springmvc，官方建议我们这样去做 @Configuration
// 应为类型要求为 WebMvcConfigurer，所以我们实现其接口
// 扩展 springmvc      DispatchServlet
// @EnableWebMvc // 这玩意就是导入了一个类，DelegatingWebMvcConfiguration，从容器中获取所有的 webMvcConfig
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送 /test2，就会跳转到 test 页面
        registry.addViewController("/test2").setViewName("test");


        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }


    // 自定义的国际化组件就生效了
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }
}

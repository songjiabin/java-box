package com.demo.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize -> authorize
//                // 首页、登录页和静态资源允许未登录访问。
//                .requestMatchers("/", "/index", "/tologin", "/css/**", "/js/**").permitAll()
//                // 其他请求必须经过认证。
//                .anyRequest().authenticated())
//                .formLogin(form -> form
//                // 指定自定义登录页面地址。
//                .loginPage("/tologin")
//                // 登录页面本身必须允许未登录访问。
//                .permitAll()).logout(logout -> logout
//                // 注销成功后返回首页。
//                .logoutSuccessUrl("/index")
//                // 注销接口允许已登录用户访问。
//                .permitAll());
//
//        return http.build();
//
//    }

}

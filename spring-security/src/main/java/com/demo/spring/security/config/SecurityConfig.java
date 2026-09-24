package com.demo.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        // 推荐使用，它在每次加密时都会生成不同的盐值，且计算成本可调
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 关闭 CSRF 如果使用的是Cookie相关的登录保存等，那么这里不要关闭
        http.csrf(csrf -> csrf.disable())
                // 不使用 Session 保存认证状态
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 配置访问权限
                .authorizeHttpRequests(auth -> auth
                        // 登录接口允许未登录访问
                        .requestMatchers("/user/login").permitAll()
                        // 如果 /hello 也想公开访问，可以放开这一行
                        // .requestMatchers("/hello").permitAll()
                        // 其他请求必须认证
                        .anyRequest().authenticated());


        return http.build();

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

    }

}

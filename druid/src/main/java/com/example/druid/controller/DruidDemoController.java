package com.example.druid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供用于触发 WebStatFilter 的测试接口。
 */
@RestController
public class DruidDemoController {

    /**
     * 返回简单文本，用于在 Druid 监控页面中观察 Web 请求统计。
     *
     * @return 测试接口的响应内容
     */
    @GetMapping("/jdbc/test")
    public String testRequest() {
        return "web-stat-filter-ok";
    }
}

package com.example.jdbc.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @RestController 相当于 @Controller + @ResponseBody。
 *
 * <p>区别在于：@Controller 的方法返回值默认被当作「视图名」，交给模板引擎去找页面；
 * 而 @RestController 会把返回值直接写进响应体（对象会被 Jackson 序列化成 JSON）。
 *
 * <p>这两个注解都在 spring-web 里，所以必须由 spring-boot-starter-web 提供依赖。
 */
@RestController
public class JDBCController {


    final JdbcTemplate jdbcTemplate;

    public JDBCController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 查询数据库的所有信息
    // 没有实体类，获取数据库的东西，怎么获取？ Map
    @GetMapping("/userList")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        return jdbcTemplate.queryForList(sql);
    }


    @GetMapping("/addUser")
    public String addUser() {
        String sql = "insert into mybatis.user(id,name,pwd) values(7,'小明','123456')";
        jdbcTemplate.execute(sql);
        return "update-ok";
    }


    // @GetMapping("/updateUser/{id}") 定义了一个带动态路径参数的 GET 请求。
    // URL 中的 {id} 是路径占位符，例如访问 /updateUser/7 时，7 会传给下面方法的 id 参数。
    // @PathVariable("id") 用于获取 URL 路径中的 id 参数，并将其转换为 int 类型。
    // @PathVariable 适用于 /updateUser/7 这种路径参数写法。
    // @RequestParam 适用于 /updateUser?id=7 这种查询参数写法。
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id) {
        String sql = "update mybatis.user set name  = ?,pwd = ? where id = " + id;
        //封装
        Object[] objects = new Object[2];

        objects[0] = "小明2";
        objects[1] = "aaaaaaa";

        jdbcTemplate.update(sql, objects);
        return "update-ok";
    }


}

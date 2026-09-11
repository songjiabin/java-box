package com.example.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试类要和启动类在同一个包（或子包）下，@SpringBootTest 才能自动找到配置类。
 * 放在默认包里是找不到的。
 */
@SpringBootTest
public class SpringbootDataJdbcApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        // 看一下默认数据源
        System.out.println("1、" + dataSource.getClass());
        // 获得连接
        Connection connection = dataSource.getConnection();
        System.out.println("2、" + connection);
        // 关闭连接
        connection.close();
    }
}

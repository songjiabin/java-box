package com.demo.x.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 将 IntelliJ 或 Maven 传入的 JVM 系统属性交给 MyBatis，用于解析数据库连接配置中的占位符。
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, System.getProperties());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取 SqlSession 连接
    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

}

import com.alibaba.druid.pool.DruidDataSource;
import com.example.druid.SpringbootDataDruidApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.Name;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest(classes = SpringbootDataDruidApplication.class)
public class MyTest {

    @Autowired
    private DataSource dataSource;


    @Test
    public void contextLoads() throws SQLException {
        // 看一下默认数据源
        System.out.println(dataSource.getClass());
        // 获得连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

        // 关闭连接
        connection.close();
    }


    // 这里看看执行一条sql
    @Test
    public void contextLoadsOfSql() throws  SQLException{
        String sql = "select  * from mybatis.user";
        // 获取数据库连接。
        try (Connection connection = dataSource.getConnection();
             // 创建 SQL 执行对象。
             Statement statement = connection.createStatement();
             // 执行查询 SQL，返回结果集。
             ResultSet resultSet = statement.executeQuery(sql)) {

            // 遍历查询结果。
            while (resultSet.next()) {
                System.out.println(
                        "id：" + resultSet.getInt("id")
                                + "，name：" + resultSet.getString("name")
                                + "，pwd：" + resultSet.getString("pwd")
                );
            }
        }
    }



}

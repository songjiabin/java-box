package com.demo.spring.boot.my.batis.mapper;


import com.demo.spring.boot.my.batis.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// 告诉 MyBatis：这是一个 Mapper 接口
// 自动为它创建代理对象
// 将接口方法和对应的 XML SQL 绑定起来
// 把生成的代理对象交给 Spring 管理
@Mapper

// 标记这是一个数据访问层组件
// 让 Spring 把它当作 Repository 管理
// 表示这个类负责数据库访问
// 支持 Spring 的数据库异常转换
@Repository
public interface UserMapper {

    List<User> queryUserList();

    User queryUserById(int id);


    int addUser(User user);


    int updateUser(User user);


    int deleteUser(int id);
}

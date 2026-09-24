package com.demo.spring.security.mapper;

import com.demo.spring.security.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {


    List<User> queryUserList();

    User queryUser(String userName);
}

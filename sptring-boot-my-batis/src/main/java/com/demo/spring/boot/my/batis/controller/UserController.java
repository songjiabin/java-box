package com.demo.spring.boot.my.batis.controller;


import com.demo.spring.boot.my.batis.bean.User;
import com.demo.spring.boot.my.batis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        List<User> usersList = userMapper.queryUserList();
        for (User user : usersList) {
            System.out.println(user);
        }
        return usersList;
    }


}

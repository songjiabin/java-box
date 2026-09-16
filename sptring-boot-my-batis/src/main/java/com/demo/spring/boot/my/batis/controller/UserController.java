package com.demo.spring.boot.my.batis.controller;


import com.demo.spring.boot.my.batis.bean.User;
import com.demo.spring.boot.my.batis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/queryUserById/{id}")
    public User queryUserById(@PathVariable(value = "id") int id) {
        System.out.println("id====>" + id);
        User user = userMapper.queryUserById(id);
        return user;
    }


    @GetMapping("/addUser")
    public String addUser() {
        int i = userMapper.addUser(new User(8, "阿毛", "123456"));
        return "ok";
    }


    @GetMapping("/updateUser")
    public String updateUser() {
        userMapper.updateUser(new User(8, "阿毛更新", "123455676"));
        return "ok";
    }


    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {
        int i = userMapper.deleteUser(id);
        return "ok";
    }

}

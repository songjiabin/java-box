package com.demo.spring.security.controller;


import com.demo.spring.security.dao.ResponseResult;
import com.demo.spring.security.dao.User;
import com.demo.spring.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginServcie;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){ return loginServcie.login(user); }
}

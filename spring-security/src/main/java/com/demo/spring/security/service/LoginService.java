package com.demo.spring.security.service;


import com.demo.spring.security.dao.ResponseResult;
import com.demo.spring.security.dao.User;
import org.springframework.stereotype.Service;


public interface LoginService {


    ResponseResult login(User user);
}

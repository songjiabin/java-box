package com.demo.spring.security.service;

import com.demo.spring.security.dao.LoginUser;
import com.demo.spring.security.dao.ResponseResult;
import com.demo.spring.security.dao.User;
import com.demo.spring.security.utils.JwtUtil;
import com.demo.spring.security.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;


@Service
public class LoginServiceImpl implements LoginService {


    @Autowired  //AuthenticationManager是负责处理用户认证的核心组件;
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;


    @Override
    public ResponseResult login(User user) {
        // AuthenticationManager authenticate 进行用户认证
        // 创建认证对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //认证成功，提供者返回一个填充了用户详细信息和授权信息的新Authentication对象
        //这个对象随后被Spring Security框架接受并存储在SecurityContextHolder中，表示用户当前的认证状态
        // Spring Security 会调用你的 UserDetailsServiceImpl.loadUserByUsername()：
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断当前认证成功、失败, 抛出接口异常;
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 使用userid生成token
        // 获取登录用户
        // getPrincipal() 获取的是认证成功后的用户对象。这里强制转换成 LoginUser，前提是 你的认证提供者确实返回了 LoginUser。
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // authenticate存入redis
        redisCache.setCacheObject("login:" + userId, loginUser);
        //把token响应给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult(200, "登陆成功", map);


    }
}

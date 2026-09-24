package com.demo.spring.security.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
//LoginUser 自定义 UserDetails对象,内部封装了当前登录用户信息: 获取用户名\密码\是否过期\...
public class LoginUser implements UserDetails {
    private User user;			//当然定义的User类,因为是重写所以暂时都是true,稍后做修改...
    @Override					//加载用户详细信息，包括用户的权限（或角色）
    public Collection<? extends GrantedAuthority> getAuthorities() { return null; }
    @Override					//获取用户密码
    public String getPassword() { return user.getPassword(); }
    @Override					//获取用户名
    public String getUsername() { return user.getUserName(); }
    @Override					//判断用户凭证是否已经过期
    public boolean isCredentialsNonExpired() { return true; }
    @Override					//判断帐号是否已经过期
    public boolean isAccountNonExpired() { return true; }
    @Override					//判断帐号是否已被锁定
    public boolean isAccountNonLocked() { return true; }
    @Override					//用户状态是否有效
    public boolean isEnabled() { return true; }

}

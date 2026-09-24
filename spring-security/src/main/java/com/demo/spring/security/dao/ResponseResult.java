package com.demo.spring.security.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {

    private Integer code;
    private String msg;
    private Object data;

}

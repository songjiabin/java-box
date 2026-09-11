package com.example.jdbc.bean;

/**
 * 对应 mybatis 库里的 user 表（id / name / pwd）。
 *
 * <p>字段名要和数据库列名保持一致，这样 BeanPropertyRowMapper 才能自动完成映射
 * （列名和属性名不同的话，可以在 SQL 里用 as 起别名）。
 */
public class User {

    private Integer id;
    private String name;
    private String pwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', pwd='" + pwd + "'}";
    }
}

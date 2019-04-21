package com.demo.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

@Table(name = "user")
@Data
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer uid;

    private String username;

    private String password;
    @Transient
    private Set<Role> roles = new HashSet<>();
}

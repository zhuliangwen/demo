package com.demo.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
@Table(name = "role")
@Data
public class Role {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long rid;

    private String rname;

    private Set<Permission> permissions = new HashSet<>();

    private Set<User> users = new HashSet<>();
}

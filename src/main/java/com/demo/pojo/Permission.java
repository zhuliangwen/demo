package com.demo.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "permission")
@Data
public class Permission {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer pid;

    private String name;

    private String url;
}
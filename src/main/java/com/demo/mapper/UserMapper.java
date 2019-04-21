package com.demo.mapper;

import com.demo.common.CommonMapper;
import com.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends CommonMapper<User> {

    User findByUsername(@Param("username") String username);

    User getUserById(@Param("id") Long id);
}

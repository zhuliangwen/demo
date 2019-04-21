package com.demo.service.impl;

import com.demo.annotation.TargetDateSource;
import com.demo.enums.DataSourceKey;
import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        User user = userMapper.getUserById(id);
        if (user == null) {
            // todo 抛出自定义异常   数据不存在
            System.out.println("抛出自定义异常  BAD_REQUEST  ");
        }
        return user;
    }

    @TargetDateSource(dataSourceKey = DataSourceKey.DB_OTHER)
    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            // todo   抛出异常
            System.out.println("抛出自定义异常  BAD_REQUEST  ");
        }
        return user;
    }

}

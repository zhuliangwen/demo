package com.demo.service;

import com.demo.pojo.User;

public interface IUserService {

    User getUserById(Long id);

    User getUserByUsername(String username);
}

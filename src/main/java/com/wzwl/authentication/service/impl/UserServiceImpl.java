package com.wzwl.authentication.service.impl;

import com.wzwl.authentication.mapper.UserMapper;
import com.wzwl.authentication.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huff
 * @date 2020/11/10 16:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String getPasswordByName(String username) {
        return userMapper.getPasswordByName(username);
    }
}

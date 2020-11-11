package com.wzwl.authentication.service;

/**
 * @author huff
 * @date 2020/11/10 16:20
 */
public interface UserService {

    /**
     * 通过用户名查询密码
     * @param username
     * @return
     */
    String getPasswordByName(String username);

}

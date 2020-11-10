package com.wzwl.authentication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzwl.authentication.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author huff
 * @date 2020/11/10 14:35
 */
@Component
@Mapper
public interface UserMapper extends BaseMapper<User> {

    String getPasswordByName(String username);
}

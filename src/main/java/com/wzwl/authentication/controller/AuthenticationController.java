package com.wzwl.authentication.controller;

import com.wzwl.authentication.service.UserService;
import com.wzwl.authentication.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class AuthenticationController {


    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;


    /**
     *
     */
    @PostMapping("/index")
    @ResponseBody
    public ResponseInfoData index() {

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("result","测试成功");
        return ResponseInfoUtils.createSuccess(resultMap);
    }

    /**
     * 登录认证
     * @param username
     * @param password
     * @return
     */
    @GetMapping("login/check")
    @ResponseBody
    public ResponseInfoData loginCheck(@RequestParam(value = "username", required = true) String username,
                                       @RequestParam(value = "password", required = true) String password) {
        try {
            if (StringUtils.isEmpty(username)|| StringUtils.isEmpty(password)){
                return ResponseInfoUtils.createFail("用户名或密码不能为空");
            }
            String realPass = userService.getPasswordByName(username);
            if (StringUtils.isEmpty(realPass)){
                return ResponseInfoUtils.createFail("该用户名不存在");
            }
            if (realPass.equals(password)){
                String token = Md5Util.encryPassword(username+password);
                redisUtil.set(token,username,Constants.TOKEN_EXPIRED_TIME);
                Map<String,String> resultMap = new HashMap<>();
                resultMap.put("token",token);
                resultMap.put("username",username);
                return ResponseInfoUtils.createSuccess(resultMap);
            }
        }catch (Exception e){
            return ResponseInfoUtils.createFail("登陆失败");
        }
        return ResponseInfoUtils.createFail("用户名或密码错误！");
    }


}

package com.wzwl.authentication.controller;

import com.wzwl.authentication.service.UserService;
import com.wzwl.authentication.utils.CommonUtil;
import com.wzwl.authentication.utils.Md5Util;
import com.wzwl.authentication.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class AuthenticationController {


    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;


    /**
     * 定向到登陆页面
     * @return
     */
    @GetMapping("/login")
    public String test(){

        return "login";
    }


    /**
     * 跳转首页
     * @param request
     * @return
     */
    @GetMapping(value = "/index")
    public String users(HttpServletRequest request) {
        try {
            String token = CommonUtil.getTokenFromRequest(request);
            if (StringUtils.isNotEmpty(token)){
                return "index";
            }
        } catch (Exception e) {
            log.error("首页跳转发生异常exceptions-->" + e.toString());
        }
        return "login";
    }

    /**
     * 登陆认证
     * @param username
     * @param password
     * @param response
     * @param request
     * @return
     */
    @GetMapping(value = "/user/login")
    public String loginCheck(@RequestParam(value = "username", required = true) String username,
                             @RequestParam(value = "password", required = true) String password, HttpServletResponse response,
                             HttpServletRequest request) {
        try {
            if (StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)) {
                //取出密码
                String realPwd = userService.getPasswordByName(username);
                if (realPwd.equals(password)) {
                    //登陆成功
                    String token = Md5Util.encryPassword(username+password);

                    redisUtil.set(token, username, 60 * 60 * 24);
                    Cookie cookie = new Cookie("authenticationCode", token);
                    cookie.setMaxAge(60 * 30);
                    cookie.setPath("/");
                    cookie.setHttpOnly(false);

                    response.addCookie(cookie);   //登陆成功 ，返回 到index页面   todo

                    return "index";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }


}

package com.wzwl.authentication.filter;

import com.wzwl.authentication.utils.RedisUtil;
import com.wzwl.authentication.utils.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginFilter extends HandlerInterceptorAdapter {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("token");
        RedisUtil redisUtil = SpringBeanUtil.getBean(RedisUtil.class);
        if (StringUtils.isNotBlank(token)&&redisUtil.hasKey(token)){
            redisUtil.set(token,redisUtil.set(token,redisUtil.get(token),60 * 60 * 24));
            return true;
        }
        // 跳转登录
        String url = "/login";
        response.sendRedirect(url);
        return false;
    }




}

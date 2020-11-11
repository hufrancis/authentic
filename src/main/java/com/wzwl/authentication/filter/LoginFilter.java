package com.wzwl.authentication.filter;

import com.alibaba.fastjson.JSONObject;
import com.wzwl.authentication.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author huff
 */
@Slf4j
public class LoginFilter extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        RedisUtil redisUtil = SpringBeanUtil.getBean(RedisUtil.class);
        String token = request.getHeader("token");
        JSONObject object = new JSONObject();
        PrintWriter out = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        if (StringUtils.isEmpty(token)){
            object.put("code","401");
            object.put("codeDesc","未授权");
            object.put("message","未携带token");
            object.put("data","");
            out = response.getWriter();
            out.append(object.toString());
            out.close();
            return false;
        }
        if(!redisUtil.hasKey(token)){
            object.put("code","403");
            object.put("codeDesc","禁止");
            object.put("message","token错误或已过期,请重新登陆");
            object.put("data","");
            out = response.getWriter();
            out.append(object.toString());
            out.close();
            return false;
        }
        //重新刷新token的过期时间
        redisUtil.set(token,redisUtil.get(token), Constants.TOKEN_EXPIRED_TIME);
        return true;
    }

}

package com.wzwl.authentication.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        CommonUtil.sdf = sdf;
    }

    public static String getData(){
        return sdf.format(new Date());
    }


    public static String getTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        RedisUtil redisUtil = SpringBeanUtil.getBean(RedisUtil.class);
        String token = "";
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")){
                    token = cookies[i].getValue();
                }
            }
            //成功获取token
            if (token != "" && redisUtil.hasKey(token)) {
                return token;
            }
        }
        return token;
    }
}

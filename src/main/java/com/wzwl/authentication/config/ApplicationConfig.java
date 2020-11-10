package com.wzwl.authentication.config;

import com.wzwl.authentication.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author huff
 * @date 2020/8/10 14:37
 */
@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    //private static final List<String> EXCLUDE_PATH= Arrays.asList("/login","/user/login","/","/css/**","/js/**","/img/**","/media/**","/vendors/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter())
                .addPathPatterns("/**")
                //.addPathPatterns("/index")
                .excludePathPatterns("/login","/user/login","/login.html","/index.html");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET")
                .maxAge(30*60)
                .allowCredentials(true);//设置成允许操作cookie
    }
}
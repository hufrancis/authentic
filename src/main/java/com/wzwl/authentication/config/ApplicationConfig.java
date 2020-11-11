package com.wzwl.authentication.config;

import com.wzwl.authentication.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author huff
 * @date 2020/8/10 14:37
 */
@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    private static final List<String> EXCLUDE_PATH= Arrays.asList("/login/check/**","/login**",
            "/css/**", "/js/**","/img/**","/templates/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginFilter());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(EXCLUDE_PATH);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String relativePath = System.getProperty("user.dir");
        String rootPath = relativePath.split("\\\\")[0];
        String productImgName = rootPath +"/"+"images/";
        String path = "file:"+productImgName;
        registry.addResourceHandler("/images/**").addResourceLocations(path);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET")
                .maxAge(30*60)
                //设置成允许操作cookie
                .allowCredentials(true);
    }
}
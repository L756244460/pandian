package cn.datasset.yipandian.client.config;

import javax.annotation.Resource;

import cn.datasset.yipandian.client.interceptor.UserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private UserInfoInterceptor interceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截：用户信息保存
        registry.addInterceptor (interceptor)
                .addPathPatterns ("/**")
                .excludePathPatterns ("/login", "/updatePwd", "/get_verification_code");

    }
}

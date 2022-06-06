package com.example.milktea.config;


import com.example.milktea.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tcm
 *
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                //不放行的接口;通常所有不放行
                .addPathPatterns("/**")
                //放行，
                .excludePathPatterns("/userinfo/register")
                .excludePathPatterns("/userinfo/login")
                .excludePathPatterns("/admin/sendEmail")
//                放行图片
                .excludePathPatterns("/goodspic/show/**")
                .excludePathPatterns("/shopswipe/title/**")
                .excludePathPatterns("/swipe/swipe/**")
                //swagger请求
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/doc.html/**")
                .excludePathPatterns("/doc.html");

    }

}

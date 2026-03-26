package web.global.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import web.global.jwt.JwtAuthenticationFilter;
import web.global.jwt.JwtUtil;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter(JwtUtil jwtUtil) {
        FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new JwtAuthenticationFilter(jwtUtil));
        bean.addUrlPatterns("/*"); // 모든 요청 적용
        return bean;
    }
}
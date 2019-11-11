package com.bushemi;

import com.bushemi.bpp.TimedBeanPostProcessor;
import com.bushemi.web.interceptors.LogoutInterceptor;
import com.bushemi.web.interceptors.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(value = {"com.bushemi.converters",
        "com.bushemi.dao",
        "com.bushemi.service",
        "com.bushemi.controllers"})
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/views/**").addResourceLocations("/views/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSecurityInterceptor());
        registry.addInterceptor(getLogoutInterceptor());
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");

        return bean;
    }

//    @Bean
//    public TimedBeanPostProcessor getTimedBeanPostProcessor() {
//        return new TimedBeanPostProcessor();
//    }

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public LogoutInterceptor getLogoutInterceptor() {
        return new LogoutInterceptor();
    }
}

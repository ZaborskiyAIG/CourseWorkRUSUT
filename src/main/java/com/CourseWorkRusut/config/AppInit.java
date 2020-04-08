package com.CourseWorkRusut.config;

import com.CourseWorkRusut.security.jwt.JwtTokenFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Этот метод должен содержать конфигурации которые инициализируют Beans
    // для инициализации бинов у нас использовалась аннотация @Bean
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                HibernateConfig.class,
                AppSecurityConfig.class
        };
    }

    // Тут добавляем конфигурацию, в которой инициализируем ViewResolver и контроллеры
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
               WebConfig.class,
                JwtTokenFilter.class
        };
    }

    @Override
    protected String[] getServletMappings() {  //эт че за херня?
        return new String[]{"/"};
    }
}

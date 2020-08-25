package com.CourseWorkRusut.config;

import com.CourseWorkRusut.security.jwt.JwtTokenFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                HibernateConfig.class,
                AppSecurityConfig.class
        };
    }

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

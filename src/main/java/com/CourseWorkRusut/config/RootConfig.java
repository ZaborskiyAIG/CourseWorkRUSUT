package com.CourseWorkRusut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RootConfig implements WebMvcConfigurer { //че за веб конфиг? хуй знает

  //  @Override  //страница входа поумолчанию?
  //  public void addViewControllers(ViewControllerRegistry registry) {
  //      registry.addViewController("/login").setViewName("login"); //вроде как позволяет настроить начальную страницу, Но это не точно
  //  }

}

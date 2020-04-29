package com.CourseWorkRusut.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.CourseWorkRusut")
@PropertySource("classpath:heroku.properties")
public class HibernateConfig {

    private final Environment env;   //разобрать environment

    @Autowired
    public HibernateConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.CourseWorkRusut.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("name"));
        dataSource.setPassword(env.getProperty("password"));

        //<localhost database>
       //dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      // dataSource.setUrl("jdbc:mysql://localhost:3306/coursework");
      // dataSource.setUsername("");
     //  dataSource.setPassword("12345");

        return dataSource;
    }


    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.format_sql", "true");
             }
        };
    }

    @Bean(name = "multipartResolver")                   //пока так
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

}

package com.CourseWorkRusut.config;


import com.CourseWorkRusut.security.jwt.JwtConfigurer;
import com.CourseWorkRusut.security.jwt.JwtTokenProvider;
import com.CourseWorkRusut.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter { //или WebSecurityConfiguration ?? чек разницу


    private final JwtTokenProvider jwtTokenProvider;

    private UserDetailsServiceImpl userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppSecurityConfig(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }


    // @Bean
  //  public BCryptPasswordEncoder bCryptPasswordEncoder() {
  //      return new BCryptPasswordEncoder();
  //  }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors().  //httpBasic почитать
                and()
                .csrf()
                    .disable()
                .authorizeRequests()//.sessionManagement //почитать, впринцыпе узнать про сессии в секьюрности
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/admin/**").hasRole("ADMIN")
              //  .antMatchers("/news").hasRole("USER")
                //Доступ разрешен всем пользователям
                .antMatchers("/login").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));

                /*
                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/user", true)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/user");

                 */
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); //можно сделать через this.au..., но тогда нужно использовать конфигГлобал, в чем разница?
    }

  //  @Autowired
  //  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  //      auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  //  }

}

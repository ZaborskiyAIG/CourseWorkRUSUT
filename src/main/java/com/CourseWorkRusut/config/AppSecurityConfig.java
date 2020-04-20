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
                .cors().  //httpBasic и csrf почитать
                and()
                .csrf()
                    .disable()
                .authorizeRequests()//.sessionManagement //почитать, впринцыпе узнать про сессии в секьюрности
                    .antMatchers("/registration").not().fullyAuthenticated()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/login","/s").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .apply(new JwtConfigurer(jwtTokenProvider));

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

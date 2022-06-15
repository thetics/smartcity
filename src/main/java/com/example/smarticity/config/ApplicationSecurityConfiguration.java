package com.example.smarticity.config;

import com.example.smarticity.data.service.services.UserService;
import com.example.smarticity.data.service.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)

public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userDetailsService;
    private final BCryptPasswordEncoder encoder;


    public ApplicationSecurityConfiguration(UserServiceImpl userDetailsService, BCryptPasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

//        @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().ignoringAntMatchers("/**");
//        httpSecurity.authorizeRequests().antMatchers("/**").permitAll();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .antMatchers("/reviews/create")
//                .permitAll()
//                .antMatchers("/reviews/**").hasAnyAuthority( "ADMIN");

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/users/register", "/users/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .defaultSuccessUrl("/admin/index").permitAll()

                .failureUrl("/login.html?error=true")
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized");


    }


    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }



}

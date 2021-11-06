package com.spotify.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin").password("admin")
                .roles("USER", "ADMIN")
                .and()
                .withUser("user").password("user")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/h2-console/**").permitAll()
            //    .antMatchers("/homePage").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/userPage").access("hasRole('ROLE_USER')")
                .antMatchers("/adminPage").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/loginPage")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/loginPage?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/loginPage?logout");

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new UrlAuthenticationSuccessHandler();
    }
}

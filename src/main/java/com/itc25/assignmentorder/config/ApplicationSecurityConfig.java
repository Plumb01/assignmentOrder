package com.itc25.assignmentorder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public ApplicationSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/register").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/auth").permitAll()

                .antMatchers("/duty/insert").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/member/*").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/assignment/*").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/assignmentHistory/approve").hasAnyAuthority("ROLE_CHAIRMAN")
                .antMatchers("/assignmentHistory/insert").hasAnyAuthority("ROLE_ADMIN")
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder(10);
    }

}

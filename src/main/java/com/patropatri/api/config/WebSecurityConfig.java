package com.patropatri.api.config;

import com.patropatri.api.config.jwt.JwtAuthenticationEntryPoint;
import com.patropatri.api.config.jwt.JwtRequestFilter;
import com.patropatri.api.utils.ConstantKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint mEntryPoint;

    @Autowired
    private UserDetailsService mService;

    @Autowired
    private JwtRequestFilter mFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load user for matching credentials by use BCryptPasswordEncoder
        auth.userDetailsService(mService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(Arrays.asList("*"));
            cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(Arrays.asList("*"));
            return cors;}) //CORS Missing Allow Origin
                .and().csrf().disable().authorizeRequests() // disabled CSRF
                .antMatchers("/**").permitAll()
                //.antMatchers("/ws/**").permitAll()
                .antMatchers(ConstantKey.SIGN_UP_URL).permitAll() // don't authenticate this particular request
                .antMatchers(ConstantKey.SIGN_IN_URL).permitAll() // OR .antMatchers("/authenticate", "/register").permitAll().
                .antMatchers(ConstantKey.RESOURCE_URL).permitAll()
                .antMatchers(ConstantKey.GUEST_USER_URL).permitAll()
                .antMatchers(ConstantKey.SOCKET_URL).permitAll()
                .anyRequest().authenticated() // all other requests need to be authenticated
                .and()
                .exceptionHandling().authenticationEntryPoint(mEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this disables session creation on Spring Security
        http.addFilterBefore(mFilter, UsernamePasswordAuthenticationFilter.class); // Add a filter to validate the tokens with every request
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(ConstantKey.SIGN_UP_URL).permitAll()
                .antMatchers(ConstantKey.SIGN_IN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthNFilter(authenticationManager()))
                .addFilter(new AuthZFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable();
    }*/
}

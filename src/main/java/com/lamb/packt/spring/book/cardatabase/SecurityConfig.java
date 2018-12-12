package com.lamb.packt.spring.book.cardatabase;

import com.lamb.packt.spring.book.cardatabase.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    // We are defining that the POSt method request to the "/login" endpoint is allowed w/o authentication
    // and all other endpoints need authentication. We are then defining the filters to be used for the "/login" endpoint
    // and all other endpoints via the 'addFilterBefore' method.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.csrf().disable().cors().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated().and()
                //Filter for the api/login requests
                .addFilterBefore( new LoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // Filter for other requests to check JWT in Header
                .addFilterBefore( new AuthenticationFilter(),
                                    UsernamePasswordAuthenticationFilter.class);
    }

    // This method allows CORS (Cross-Origin Resource Sharing) filter.
    // It is needed for the frontend that's sending requests from the other origin.
    // CORS filter will intercept requests and if identified as Cross-Origin it adds proper headers to the request
    // This example we are allowing all HTTP methods and headers
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        config.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Tells Spring Security to grab/save the user information from/to the database and uses BCrypt to hash
        // the password before saving it to the db
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }


}

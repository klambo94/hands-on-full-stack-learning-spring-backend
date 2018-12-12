package com.lamb.packt.spring.book.cardatabase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamb.packt.spring.book.cardatabase.domain.AccountCredentials;
import com.lamb.packt.spring.book.cardatabase.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

// Handles POST requests to "/login" endpoint
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }


    // Authentication is preformed here.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res)
                                    throws AuthenticationException, IOException, ServletException {

        AccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
    }


    // If successful authentication, this method will then be executed
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication auth)
                    throws IOException, ServletException {

        // This calls the addToken method in our AuthenticationService class
        // and the token will be added to the Authorization header
        AuthenticationService.addToken(res, auth.getName());
    }
}

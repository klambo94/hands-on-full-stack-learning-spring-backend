package com.lamb.packt.spring.book.cardatabase;

import com.lamb.packt.spring.book.cardatabase.service.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// GenericFilterBean is a generic superclass for any type of filter
// Handles the authentication in all other endpoints exception "/login"

/* Uses the addAuthentication method from the AuthenticationService class to get
 * a token from the request Authorization header
 */
public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
                throws IOException, ServletException {

        Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest) request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}

package com.lamb.packt.spring.book.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;


public class AuthenticationService {

    static final long EXPIRATION_TIME = 864_000_00; //1 day in miliseconds
    static final String SIGNING_KEY = "SecretKey";
    static final String PREFIX = "Bearer";

    // Creates the token and adds it to the request's Authorization Header
    public static void addToken(HttpServletResponse res, String username) {

        //Creating the JwtToken
        String JwtToken = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();

        //Adding the token to the headers
        res.addHeader("Authorization", PREFIX + " " + JwtToken);

        // The "Access-Control-Expose-Headers" header is needed because by default we are unable to access
        // the "Authorization" header through a JavaScript frontend
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    //Retrieves the token from the response Authorization header via Jwts.parser()
    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null ){
            String user = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

           if(user != null) {
               return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
           }
        }

        return null;
    }
}

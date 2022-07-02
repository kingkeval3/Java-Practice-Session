package com.java.demo.security;

import com.java.demo.datastore.model.UserModel;
import com.java.demo.services.impl.UserServiceImpl;
import com.java.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationToken = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if(authorizationToken != null && !authorizationToken.isBlank() && authorizationToken.startsWith("Bearer ")) {

            jwtToken = authorizationToken.substring(7);

            username = jwtUtils.extractUsername(jwtToken);

        }

        if(username != null && !username.isBlank() /*&& SecurityContextHolder.getContext() == null*/) {

            UserDetails userDetails = userServiceImpl.loadUserByUsername(username);

            Boolean isValidated = jwtUtils.validateToken(jwtToken, userDetails);

            if (isValidated) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

            filterChain.doFilter(request, response);
    }
}

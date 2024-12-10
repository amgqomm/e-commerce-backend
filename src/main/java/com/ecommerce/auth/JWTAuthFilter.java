/**
 * @author Enkh-Amgalan G.
 *
 * @description This filter processes each request to validate JWT tokens for authentication.
 * It extracts the token from the Authorization header, validates it, and sets the security context if the token is valid.
 */
package com.ecommerce.auth;

import com.ecommerce.auth.JWTUtils;
import com.ecommerce.service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * Filter for handling JWT authentication in the application.
 * Executes once per request to validate and process JWT tokens.
 */
@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailService userDetailService;

    /**
     * Filters incoming HTTP requests, validating JWT tokens and setting the authentication context if valid.
     *
     * @param request     the HttpServletRequest object.
     * @param response    the HttpServletResponse object.
     * @param filterChain the FilterChain object to continue the request processing.
     * @throws ServletException in case of servlet errors.
     * @throws IOException      in case of input/output errors.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        // Check if the Authorization header is missing or blank
        if (authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token (assumes "Bearer " prefix)
        jwtToken = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(jwtToken);

        // Validate the token and set the security context if valid
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailService.loadUserByUsername(userEmail);

            // Create a new security context
            if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                // Create an authentication token and set it in the security context
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
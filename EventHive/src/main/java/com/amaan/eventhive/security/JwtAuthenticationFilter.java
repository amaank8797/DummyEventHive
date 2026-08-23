package com.amaan.eventhive.security;

import com.amaan.eventhive.entity.User;
import com.amaan.eventhive.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil,
                                   UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response,
            javax.servlet.FilterChain filterChain)
            throws javax.servlet.ServletException, java.io.IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                User user = userRepository.findByEmail(email)
                        .orElse(null);
                if (user != null) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user.getEmail(),
                                    null,
                                    new ArrayList<>()
                            );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);

                    System.out.println("JWT AUTHENTICATED: "
                            + SecurityContextHolder.getContext()
                            .getAuthentication()
                            .isAuthenticated());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}